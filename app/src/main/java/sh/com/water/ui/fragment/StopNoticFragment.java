package sh.com.water.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.adapter.StopWaterNoticAdapter;
import sh.com.water.bean.StopWaterNoticBean;
import sh.com.water.common.ServerConfig;
import sh.com.water.ui.activity.StopWaterActivity;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;


/**
 * Created by Administrator on 2017/9/18.
 */

public class StopNoticFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.stop_water_notic_list)
    ListView stopWaterNoticList;
    private List<StopWaterNoticBean.NoticeInfoBean> mList = new ArrayList<>();
    private StopWaterNoticBean sysmentNoticBean;
    private StopWaterNoticAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stop_notic, null);
        unbinder = ButterKnife.bind(this, view);
        request();
        initDate();
        return view;
    }

    private void initDate() {
        mAdapter = new StopWaterNoticAdapter(getActivity(), mList, R.layout.adapter_text);
        stopWaterNoticList.setAdapter(mAdapter);
        stopWaterNoticList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), StopWaterActivity.class);
                intent.putExtra("id", mList.get(i).getId() + "");
                startActivity(intent);
            }
        });
    }

    protected void request() {
        LoadingDialog.createLoadingDialog(getActivity(), "正在加载...");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
