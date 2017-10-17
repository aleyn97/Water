package sh.com.water.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import sh.com.water.adapter.BusGuideAdapter;
import sh.com.water.bean.BusGuideBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

public class BusGuideActivity extends BaseActivity {
    @BindView(R.id.guide_list)
    ListView guideList;
    private List<BusGuideBean.GuideInfoBean> mList = new ArrayList<>();
    private BusGuideBean busGuideBean;
    private BusGuideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_guide);
        ButterKnife.bind(this);
        setTitle(getString(R.string.BusGuide));
        request();
        initData();
    }

    private void initData() {
        adapter = new BusGuideAdapter(this, mList, R.layout.adapter_fold_outside);
        guideList.setAdapter(adapter);
        guideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", mList.get(i).getId());
                startActivity(BusGuildFoldActivity.class, bundle);
            }
        });
    }

    private void request() {
        LoadingDialog.createLoadingDialog(this, "正在加载...");
        OkHttpUtils
                .get(ServerConfig.USER_GUIDE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        busGuideBean = JsonMananger.jsonToBean(s, BusGuideBean.class);
                        mList.addAll(busGuideBean.getGuideInfo());
                        adapter.notifyDataSetChanged();
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

