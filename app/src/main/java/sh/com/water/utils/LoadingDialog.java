package sh.com.water.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.widget.TextView;

import sh.com.water.R;

/**
 * Created by Administrator on 2017/9/22.
 */

public class LoadingDialog extends Dialog {
    private TextView tv_text;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.progressbar_loading);
        tv_text = (TextView) findViewById(R.id.tv_text);
        setCanceledOnTouchOutside(false);
    }


    public LoadingDialog setMessage(String message) {
        tv_text.setText(message);
        return this;
    }

}
