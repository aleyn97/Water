package sh.com.water.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.adapter.StopWaterNoticAdapter;
import sh.com.water.bean.StopWaterNoticBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

public class NoticeActivity extends BaseActivity {

    @BindView(R.id.stop_water_notic_list)
    ListView stopWaterNoticList;
    private List<StopWaterNoticBean.NoticeInfoBean> mList = new ArrayList<>();
    private StopWaterNoticBean sysmentNoticBean;
    private StopWaterNoticAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bus_query);
        ButterKnife.bind(this);
        setTitle("停水通知");
        request();
        initDate();
    }

    private void initDate() {
        mAdapter = new StopWaterNoticAdapter(this, mList, R.layout.adapter_text);
        stopWaterNoticList.setAdapter(mAdapter);
        stopWaterNoticList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(NoticeActivity.this, StopWaterActivity.class);
                intent.putExtra("id", mList.get(i).getId() + "");
                startActivity(intent);
            }
        });
    }

    private void request() {
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get(ServerConfig.STOP_NOTIC)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        sysmentNoticBean = JsonMananger.jsonToBean(s, StopWaterNoticBean.class);
                        mList.addAll(sysmentNoticBean.getNoticeInfo());
                        mAdapter.notifyDataSetChanged();
                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LoadingDialog.closeDialog();
                    }
                });
    }
}
