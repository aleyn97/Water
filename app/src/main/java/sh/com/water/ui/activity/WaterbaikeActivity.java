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
import sh.com.water.adapter.WaterBaikeAdapter;
import sh.com.water.bean.WaterBaiKeBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

public class WaterbaikeActivity extends BaseActivity {

    @BindView(R.id.fold_list)
    ListView foldList;
    private List<WaterBaiKeBean.WikiInfoBean> mList = new ArrayList<>();
    private WaterBaiKeBean waterBaiKeBean;
    private WaterBaikeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterbaike);
        ButterKnife.bind(this);
        setTitle(getString(R.string.wate_baike));
        reauest();
        initData();
    }

    private void initData() {
        adapter = new WaterBaikeAdapter(mList, this);
        foldList.setAdapter(adapter);
        foldList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", mList.get(i).getId());
                startActivity(WaterBaikeFoldActivity.class, bundle);
            }
        });
    }


    protected void reauest() {
        LoadingDialog.createLoadingDialog(this, "加载中...");
        OkHttpUtils
                .get(ServerConfig.WATER_BAIKE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        waterBaiKeBean = JsonMananger.jsonToBean(s, WaterBaiKeBean.class);
                        mList.addAll(waterBaiKeBean.getWikiInfo());
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
