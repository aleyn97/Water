package sh.com.water.ui.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

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
import sh.com.water.bean.WaterBaikeFoldBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

public class WaterBaikeFoldActivity extends BaseActivity {

    @BindView(R.id.baike_fold_img)
    ImageView baikeFoldImg;
    @BindView(R.id.baike_fold_title)
    TextView baikeFoldTitle;
    @BindView(R.id.count_text)
    TextView countText;
    @BindView(R.id.baike_name)
    TextView baikeName;
    @BindView(R.id.baike_time_text)
    TextView baikeTimeText;
    private WaterBaikeFoldBean waterBaikeFoldBean;
    private List<WaterBaikeFoldBean.WikiInfoBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_baike_fold);
        ButterKnife.bind(this);
        setTitle("百科详情");
        request();
    }

    private void request() {
        final Bundle bundle = getIntent().getExtras();
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get(ServerConfig.WATER_BAIKE_FOLD)
                .params("WikiID", bundle.getInt("id") + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        waterBaikeFoldBean = JsonMananger.jsonToBean(s, WaterBaikeFoldBean.class);
                        mList.add(waterBaikeFoldBean.getWikiInfo());
                        initViews();
                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LoadingDialog.closeDialog();
                    }
                });
    }

    private void initViews() {
        if (waterBaikeFoldBean.getWikiInfo().getFILE_PATH0() != null) {
            Picasso.with(this).load(waterBaikeFoldBean.getWikiInfo().getFILE_PATH0()).into(baikeFoldImg);
        }
        baikeFoldTitle.setText(waterBaikeFoldBean.getWikiInfo().getWiki_title());
        countText.setMovementMethod(ScrollingMovementMethod.getInstance());
        countText.setText(waterBaikeFoldBean.getWikiInfo().getWiki_Content());
        baikeName.setText(waterBaikeFoldBean.getWikiInfo().getStaff_Name());
        baikeTimeText.setText(waterBaikeFoldBean.getWikiInfo().getWiki_Time() + "");
    }
}
