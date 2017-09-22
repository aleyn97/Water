package sh.com.water.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ToastUtils {
    private Context mContext;

    public ToastUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void show(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
