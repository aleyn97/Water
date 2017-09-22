package sh.com.water.ui;

import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;

import sh.com.water.utils.ToastUtils;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MyApplication extends Application {
    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtils.init(this);
    }
}
