package sh.com.water.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import sh.com.water.adapter.IllegaListAdapter;
import sh.com.water.bean.IllegaListBean;
import sh.com.water.common.ServerConfig;
import sh.com.water.ui.MyApplication;
import sh.com.water.ui.activity.IllegaDetilseActivity;
import sh.com.water.utils.JsonMananger;
import sh.com.water.utils.LoadingDialog;


/**
 * Created by Administrator on 2017/9/18.
 */

public class IllegalFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.fag_Illega_list)
    ListView fagIllegaList;
    private List<IllegaListBean> mList = new ArrayList<>();
    private IllegaListAdapter listAdapter;
    MyApplication app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_illega_list, null);
        unbinder = ButterKnife.bind(this, view);
        app = (MyApplication) getActivity().getApplication();
        request();
        initDate();
        return view;
    }

    private void initDate() {
        fagIllegaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), IllegaDetilseActivity.class);
                intent.putExtra("id", mList.get(i).getId() + "");
                startActivity(intent);
            }
        });
    }

    protected void request() {
        LoadingDialog.createLoadingDialog(getActivity(), "正在加载...");
        OkHttpUtils
                .get(ServerConfig.ILLEGA_LIST)
                .params("loginPhone", app.getUsername())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LoadingDialog.closeDialog();
                        mList = JsonMananger.jsonToList(s, IllegaListBean.class);
                        if (!mList.isEmpty()) {
                            listAdapter = new IllegaListAdapter(getActivity(), mList, R.layout.adapter_illega_list);
                            fagIllegaList.setAdapter(listAdapter);
                            listAdapter.notifyDataSetChanged();
                        } else {

                        }
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
