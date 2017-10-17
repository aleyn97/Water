package sh.com.water.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.bean.StopWaterDetailBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;

public class StopWaterActivity extends BaseActivity {

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
        OkHttpUtils
                .get(ServerConfig.STOP_WATER_DETIAL)
                .params("NoticeID", intent.getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        stopWaterDetailBean = JsonMananger.jsonToBean(s, StopWaterDetailBean.class);
                        initDate();
                    }
                });
    }

    private void initDate() {
        stopDeaTitle.setText(stopWaterDetailBean.getNoticeInfo().getNotice_title());
        stopCountText.setMovementMethod(ScrollingMovementMethod.getInstance());
        stopCountText.setText(stopWaterDetailBean.getNoticeInfo().getNotice_Content());
        stopNameText.setText(stopWaterDetailBean.getNoticeInfo().getStaff_Name());
        stopTimeText.setText(stopWaterDetailBean.getNoticeInfo().getNotice_Time() + "");
    }

}
