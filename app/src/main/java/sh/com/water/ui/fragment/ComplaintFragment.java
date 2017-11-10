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
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
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
import sh.com.water.adapter.CommonAdapter;
import sh.com.water.adapter.IllegaListAdapter;
import sh.com.water.adapter.ViewHolder;
import sh.com.water.bean.ComplainListBean;
import sh.com.water.bean.IllegaListBean;
import sh.com.water.common.ServerConfig;
import sh.com.water.ui.MyApplication;
import sh.com.water.ui.activity.ComplainDetilseActivity;
import sh.com.water.ui.activity.IllegaDetilseActivity;
import sh.com.water.utils.DatetoStringFormt;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;

/**
 * Created by Administrator on 2017/9/19.
 */

public class ComplaintFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.fag_Complain_list)
    ListView ListView;
    public List<ComplainListBean> mList = new ArrayList<>();
    MyApplication app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_report, null);
        unbinder = ButterKnife.bind(this, view);
        app = (MyApplication) getActivity().getApplication();
        request();
        return view;
    }

    private void request() {
//        LoadingDialog.createLoadingDialog(getActivity(), getString(R.string.Loading));
        LogUtils.d("开始请求");
        OkHttpUtils
                .get(ServerConfig.COMPLAINT_LIST)
                .params("loginPhone", app.getUsername())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mList = JsonMananger.jsonToList(s, ComplainListBean.class);
                        if (!mList.isEmpty()) {
                            initDate(mList);
                        }
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
//                        LoadingDialog.closeDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.showShort(getString(R.string.ServerError));
                    }
                });
    }

    private void initDate(final List<ComplainListBean> list) {
        ListView.setAdapter(new CommonAdapter<ComplainListBean>(getActivity(), list, R.layout.adapter_complain_list) {
            @Override
            public void convert(ViewHolder helper, ComplainListBean item) {
                LogUtils.d(item.getComplaints_Time() + "");
                if (item.getComplaints_OK() == 1) {
                    helper.setText(R.id.Complain_state, "已处理");
                } else {
                    helper.setText(R.id.Complain_state, "未处理");
                }
                helper.setText(R.id.Complain_time, DatetoStringFormt.StringToStrLong(item.getComplaints_Time() + ""));
            }
        });
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ComplainDetilseActivity.class);
                intent.putExtra("id", list.get(i).getId() + "");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
