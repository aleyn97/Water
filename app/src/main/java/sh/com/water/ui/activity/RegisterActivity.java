package sh.com.water.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.RegexUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.sms.SMSSDK;
import cn.jpush.sms.listener.SmscheckListener;
import cn.jpush.sms.listener.SmscodeListener;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.ClearEditText;
import sh.com.water.utils.MyCountDownTimer;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.res_phone)
    ClearEditText resPhone;
    @BindView(R.id.yan_Z_ma)
    ClearEditText yanZMa;
    @BindView(R.id.lin_code)
    LinearLayout linCode;
    @BindView(R.id.set_pwd)
    ClearEditText setPwd;
    @BindView(R.id.set_pwd2)
    ClearEditText setPwd2;
    @BindView(R.id.lin_pwd)
    LinearLayout linPwd;
    @BindView(R.id.huo_qu)
    Button huoQu;
    private String phone;
    private String Code;
    MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setTitle("用户注册");
        SMSSDK.getInstance().initSdk(this);
        SMSSDK.getInstance().setIntervalTime(60000);
        myCountDownTimer = new MyCountDownTimer(60000, 1000, huoQu);
    }

    @OnClick({R.id.huo_qu, R.id.bt_zhuce, R.id.pwd_ok})
    public void onViewClicked(View view) {
        phone = resPhone.getText().toString();
        Code = yanZMa.getText().toString();
        switch (view.getId()) {
            case R.id.huo_qu:
                if (!RegexUtils.isMobileExact(phone)) {
                    Toast.makeText(RegisterActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    request(phone);
                }
                break;
            case R.id.bt_zhuce:
                if (Code.equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else {
                    checkCodeAsyn(phone, Code);
                }
                break;
            case R.id.pwd_ok:
                String pwd1 = setPwd.getText().toString();
                String pwd2 = setPwd2.getText().toString();
                if (pwd1.length() < 6 || pwd1.length() > 10) {
                    Toast.makeText(RegisterActivity.this, "密码只能是6-8位字符", Toast.LENGTH_SHORT).show();
                } else if (pwd1.equals(pwd2)) {
                    reqCodepwd(phone, pwd1);
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不同", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void checkCodeAsyn(String phone, String code) {
        SMSSDK.getInstance().checkSmsCodeAsyn(phone, code, new SmscheckListener() {
            @Override
            public void checkCodeSuccess(String s) {
                Toast.makeText(RegisterActivity.this, "验证成功，请设置密码" + s, Toast.LENGTH_SHORT).show();
                linPwd.setVisibility(View.VISIBLE);
                linCode.setVisibility(View.GONE);
            }

            @Override
            public void checkCodeFail(int i, String s) {
                Toast.makeText(RegisterActivity.this, "验证码不正确" + s.toString() + i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCode(String phone, String code) {
        SMSSDK.getInstance().getSmsCodeAsyn(phone, code, new SmscodeListener() {
            @Override
            public void getCodeSuccess(String s) {
                Toast.makeText(RegisterActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void getCodeFail(int i, String s) {
                Toast.makeText(RegisterActivity.this, "获取失败,请检验手机号是否正确", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void request(final String phone) {
        OkHttpUtils
                .get(ServerConfig.PHONE_IF_URL)
                .params("regphone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject object = JSONObject.parseObject(s);
                        if (object.getString("msg").equals("该手机号合法！")) {
                            getCode(phone, "1");
                            myCountDownTimer.start();
                        } else {
                            Toast.makeText(RegisterActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void reqCodepwd(String phone, String pwd) {
        OkHttpUtils
                .get(ServerConfig.PWD_SET_URL)
                .params("regPhone", phone)
                .params("regPwd", pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Toast.makeText(RegisterActivity.this, "设置成功请登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
