package sh.com.water.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(PresonCenterActivity.this, app.getUsername(), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(PresonCenterActivity.this, "这是绑定", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(PresonCenterActivity.this, "这是订单", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(PresonCenterActivity.this, "这是申请", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(PresonCenterActivity.this, "这是推荐", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(PresonCenterActivity.this, "这是问卷", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(PresonCenterActivity.this, "这是建议", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(PresonCenterActivity.this, "这是版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initData() {
        app = (MyApplication) getApplication();
        int[] image1 = new int[]{R.mipmap.login_me, R.mipmap.login_band,
                R.mipmap.login_order_query, R.mipmap.login_shenqing,
                R.mipmap.login_tuijian, R.mipmap.login_diaocha,
                R.mipmap.login_jianyi, R.mipmap.login_huanyu};
        String[] text1 = new String[]{"个人信息", "用户绑定", "订单查询", "我的业务申请进度",
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
