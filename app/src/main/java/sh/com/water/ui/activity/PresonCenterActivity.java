package sh.com.water.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sh.com.water.R;
import sh.com.water.adapter.LoginAdapter;
import sh.com.water.common.BaseActivity;
import sh.com.water.ui.MyApplication;

public class PresonCenterActivity extends BaseActivity {

    @BindView(R.id.my_list)
    ListView mListview;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.bt_exit)
    Button btExit;
    @BindView(R.id.login)
    Button login;
    private List<Map<String, Object>> mList;
    private LoginAdapter adapter;
    MyApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preson_center);
        ButterKnife.bind(this);
        setTitle(getString(R.string.loginTitle));
        app = (MyApplication) getApplication();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        phone.setText(app.getUsername());
        if (!phone.getText().equals("")) {
            btExit.setVisibility(View.VISIBLE);
            login.setVisibility(View.INVISIBLE);
        }
    }

    private void listItemOnclick(int i) {
        switch (i) {
            case 0:
                Jump(PassWordActivity.class);
                break;
            case 1:
                Jump(BindUserActivity.class);
                break;
            case 2:
                startActivity(OrderQueryActivity.class);
//                Jump(OrderQueryActivity.class);
                break;
            case 3:
//                Jump(BindUserActivity.class);
                break;
            case 4:
//                Jump(BindUserActivity.class);
                break;
            case 5:
//                Jump(BindUserActivity.class);
                break;
            case 6:
                startActivity(ComplaActivity.class);
                break;
            case 7:
                startActivity(VersionActivity.class);
//                ToastUtils.showShort("已是最新版本");
                break;
        }
    }

    protected void Jump(Class<?> activity) {
        if (!app.getUsername().equals("")) {
            startActivity(activity);
        } else {
            ToastUtils.showShort("您还未登录！");
        }
    }

    private void initData() {
        int[] image1 = new int[]{R.mipmap.login_me, R.mipmap.login_band,
                R.mipmap.login_order_query, R.mipmap.login_shenqing,
                R.mipmap.login_tuijian, R.mipmap.login_diaocha,
                R.mipmap.login_jianyi, R.mipmap.login_huanyu};
        String[] text1 = new String[]{"密码管理", "用户绑定", "订单查询", "我的业务申请进度",
                "推荐人", "问卷调查", "投诉建议", "关于版本"};
        mList = new ArrayList<>();
        for (int i = 0; i < image1.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image1[i]);
            map.put("text", text1[i]);
            mList.add(map);
        }
        adapter = new LoginAdapter(mList, this);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listItemOnclick(i);
            }
        });
    }

    @OnClick({R.id.bt_exit, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_exit:
                app.setUsername("");
                finish();
                startActivity(PresonCenterActivity.class);
                break;
            case R.id.login:
                startActivityForResult(LoginActivity.class, null, 1);
                break;
        }
    }

}
