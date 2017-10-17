package sh.com.water.ui.activity;

import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

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

public class BindUserActivity extends BaseActivity {

    @BindView(R.id.ed_bind_userId)
    ClearEditText edBindUserId;
    @BindView(R.id.ed_bind_name)
    ClearEditText edBindName;
    @BindView(R.id.ed_bind_IDCade)
    ClearEditText edBindIDCade;
    LoadingDialog dialog;
    private List<ClearEditText> views = new ArrayList<>();
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_user);
        ButterKnife.bind(this);
        setTitle("用户绑定");
        dialog = new LoadingDialog(this);
        app = (MyApplication) getApplication();
    }

    @OnClick(R.id.bt_user)
    public void onViewClicked() {
        if (ifNull() == null) {
            if (RegexUtils.isIDCard18(views.get(2).getText().toString())) {
                request(views);
            } else {
                ToastUtils.showShort("身份证格式不正确");
                views.get(2).setShakeAnimation();
                views.get(2).setBackgroundResource(R.drawable.shape_error_bg);
            }
        } else {
            ToastUtils.showShort(ifNull().getHint().toString() + "不能为空");
        }
    }

    public ClearEditText ifNull() {
        views.add(edBindUserId);
        views.add(edBindName);
        views.add(edBindIDCade);
        String type;
        for (int i = 0; i < views.size(); i++) {
            type = views.get(i).getText().toString();
            if (type.equals("")) {
                views.get(i).setShakeAnimation();
                views.get(i).setBackgroundResource(R.drawable.shape_error_bg);
                return views.get(i);
            }
        }
        return null;
    }

    protected void request(List<ClearEditText> views) {
        dialog.setMessage("正在绑定").show();
        OkHttpUtils
                .get(ServerConfig.BIND_USER)
                .params("userNumber", views.get(0).getText().toString())
                .params("userName", views.get(1).getText().toString())
                .params("UserIdCard", views.get(2).getText().toString())
                .params("LoginPhone", app.getUsername())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject json = JSONObject.parseObject(s);
                        dialog.dismiss();
                        ToastUtils.showShort(json.get("msg").toString());
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dialog.dismiss();
                        ToastUtils.showShort("服务器或网络错误");
                    }
                });
    }
}
