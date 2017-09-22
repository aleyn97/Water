package sh.com.water.utils;

import android.os.CountDownTimer;
import android.widget.Button;
import sh.com.water.R;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MyCountDownTimer extends CountDownTimer {
    private Button bt_huoqu;

    public MyCountDownTimer(long millisInFuture, long countDownInterval, Button mButton) {
        super(millisInFuture, countDownInterval);
        this.bt_huoqu = mButton;
    }

    @Override
    public void onTick(long l) {
        bt_huoqu.setClickable(false);
        bt_huoqu.setText(l / 1000 + "s后重试");
        bt_huoqu.setBackgroundResource(R.color.HuiHei);
    }

    @Override
    public void onFinish() {
        bt_huoqu.setText("重新获取验证码");
        //设置可点击
        bt_huoqu.setClickable(true);
        bt_huoqu.setBackgroundResource(R.color.HuBlue);
    }
}
