package sh.com.water.ui.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.bean.BusOutFoldBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

public class BusGuildFoldActivity extends BaseActivity {
    @BindView(R.id.user_fold_img)
    ImageView userFoldImg;
    @BindView(R.id.user_fold_title)
    TextView userFoldTitle;
    @BindView(R.id.count_text)
    TextView countText;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.time_text)
    TextView timeText;
    private BusOutFoldBean busOutFoldBean;
    private List<BusOutFoldBean.GuideInfoBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guild_fold);
        ButterKnife.bind(this);
        setTitle("详情");
        request();
    }

    public void request() {
        Bundle bundle = getIntent().getExtras();
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get(ServerConfig.USER_GUIDE_FOLD)
                .params("GuideID", bundle.getInt("id") + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        busOutFoldBean = JsonMananger.jsonToBean(s, BusOutFoldBean.class);
                        mList.add(busOutFoldBean.getGuideInfo());
                        initViews();
                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.showShort("服务器或网异常");
                        LoadingDialog.closeDialog();
                    }
                });

    }

    private void initViews() {
        if (!(busOutFoldBean.getGuideInfo().getFILE_PATH0() == null)) {
            Picasso.with(this).load(busOutFoldBean.getGuideInfo().getFILE_PATH0()).into(userFoldImg);
        }
        userFoldTitle.setText(busOutFoldBean.getGuideInfo().getGuide_title());
        userName.setText(busOutFoldBean.getGuideInfo().getStaff_Name());
        countText.setMovementMethod(ScrollingMovementMethod.getInstance());
        countText.setText(busOutFoldBean.getGuideInfo().getGuide_Content());
        timeText.setText(busOutFoldBean.getGuideInfo().getGuide_Time() + "");

    }
}
