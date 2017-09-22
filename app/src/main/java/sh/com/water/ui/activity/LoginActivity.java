package sh.com.water.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.nightonke.jellytogglebutton.JellyToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.ui.MyApplication;
import sh.com.water.utils.ClearEditText;
import sh.com.water.utils.LoadingDialog;
import sh.com.water.utils.ToastUtils;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_ed_name)
    ClearEditText loginEdName;
    @BindView(R.id.login_ed_pwd)
    ClearEditText loginEdPwd;
    @BindView(R.id.rem_pwd)
    JellyToggleButton remPwd;
    private SharedPreferences sp;
    ToastUtils mToast;
    LoadingDialog dialog;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        setTitle(getString(R.string.login_jiemian));
        app = (MyApplication) getApplication();
        sp = this.getSharedPreferences("UserInfolist", Context.MODE_WORLD_READABLE);
        mToast = new ToastUtils(this);
        dialog = new LoadingDialog(this);
        if (sp.getBoolean("ISCHECK", false)) {
            remPwd.setChecked(true);
            loginEdName.setText(sp.getString("USER_NAME", ""));
            loginEdPwd.setText(sp.getString("PASSWORD", ""));
        }
    }

    @OnClick({R.id.rem_pwd, R.id.Register, R.id.bt_login, R.id.WangJi_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rem_pwd:
                if (remPwd.isChecked()) {
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
                break;
            case R.id.Register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.bt_login:
                String name = loginEdName.getText().toString();
                String pwd = loginEdPwd.getText().toString();
                if (name.equals("") || pwd.equals("")) {
                    mToast.show("账号或密码不能为空");
                } else {
                    if (remPwd.isChecked()) {
                        //记住用户名、密码、
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("USER_NAME", name);
                        editor.putString("PASSWORD", pwd);
                        editor.commit();
                    }
                    dialog.setMessage("正在登录").show();
                    Login(name, pwd);
                }
                break;
            case R.id.WangJi_pwd:
                break;
        }
    }

    private void Login(final String name, String pwd) {
        OkHttpUtils
                .get(ServerConfig.LOGIN_URL)
                .params("loginPhone", name)
                .params("loginPwd", pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject object = JSONObject.parseObject(s);
                        dialog.dismiss();
                        if (object.getString("loginResult").equals("SUCCESS")) {
                            app.setUsername(name);
                            mToast.show(object.getString("msg"));
                            finish();
                        } else {
                            mToast.show(object.getString("msg"));
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dialog.dismiss();
                        mToast.show("网络或服务器异常");
                    }
                });
    }
}
