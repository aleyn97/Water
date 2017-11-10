package sh.com.water.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.bean.StopWaterDetailBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.DatetoStringFormt;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

public class StopWaterActivity extends BaseActivity {

    @BindView(R.id.rel_stop_water)
    RelativeLayout relStopWater;
    private StopWaterDetailBean stopWaterDetailBean;
    @BindView(R.id.stop_dea_title)
    TextView stopDeaTitle;
    @BindView(R.id.stop_count_text)
    TextView stopCountText;
    @BindView(R.id.stop_name_text)
    TextView stopNameText;
    @BindView(R.id.stop_time_text)
    TextView stopTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_water);
        ButterKnife.bind(this);
        setTitle("停水详情");
        request();
    }

    private void request() {
        Intent intent = getIntent();
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get(ServerConfig.STOP_WATER_DETIAL)
                .params("NoticeID", intent.getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        stopWaterDetailBean = JsonMananger.jsonToBean(s, StopWaterDetailBean.class);
                        initDate();
                    }
                });
    }

    private void initDate() {
        relStopWater.setVisibility(View.VISIBLE);
        stopDeaTitle.setText(stopWaterDetailBean.getNoticeInfo().getNotice_title());
        stopCountText.setMovementMethod(ScrollingMovementMethod.getInstance());
        stopCountText.setText(stopWaterDetailBean.getNoticeInfo().getNotice_Content());
        stopNameText.setText(stopWaterDetailBean.getNoticeInfo().getStaff_Name());
        stopTimeText.setText(DatetoStringFormt.StringToStrLong(stopWaterDetailBean.getNoticeInfo().getNotice_Time() + ""));
    }

}
