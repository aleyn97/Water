package sh.com.water.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.adapter.BusOutAdapter;
import sh.com.water.bean.BusOutBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

public class BusoutActivity extends BaseActivity {

    @BindView(R.id.bus_map_list)
    ListView busMapList;
    private List<BusOutBean.TimeInfoBean> mList = new ArrayList<>();
    private BusOutBean busOutBean;
    private BusOutAdapter busOutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busout);
        ButterKnife.bind(this);
        setTitle(getString(R.string.Busout));
        request();
        initData();
    }

    private void initData() {
        busOutAdapter = new BusOutAdapter(this, mList, R.layout.adapter_busout);
        busMapList.setAdapter(busOutAdapter);
    }

    protected void request() {
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get(ServerConfig.BUS_OUT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        busOutBean = JsonMananger.jsonToBean(s, BusOutBean.class);
                        mList.addAll(busOutBean.getTimeInfo());
                        busOutAdapter.notifyDataSetChanged();
                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LoadingDialog.closeDialog();
                        ToastUtils.showShort("服务器或网络错误");
                    }
                });
    }
}
