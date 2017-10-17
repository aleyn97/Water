package sh.com.water.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import sh.com.water.R;
import sh.com.water.bean.BusOutBean;
import sh.com.water.ui.activity.MapActivity;

/**
 * Created by Administrator on 2017/9/30.
 */

public class BusOutAdapter extends CommonAdapter<BusOutBean.TimeInfoBean> {
    private List<BusOutBean.TimeInfoBean> mList = new ArrayList<>();
    private Context context;
    private int itemLayoutId;

    public BusOutAdapter(Context mContext, List<BusOutBean.TimeInfoBean> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
        this.mList = mDatas;
        this.context = mContext;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public void convert(ViewHolder helper, final BusOutBean.TimeInfoBean item) {
        helper.setText(R.id.phone_text, item.getPayOffice_Phone() + "");
        helper.setText(R.id.bus_title, item.getPayOffice_Name());
        String time = item.getPayOffice_StartTime() + "-" + item.getPayOffice_EndTime();
        helper.setText(R.id.summer_time, time);
        helper.setText(R.id.address_text, item.getPayOffice_Site());

        helper.getView(R.id.goto_hear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("address", item.getPayOffice_Site());
                bundle.putString("title", item.getPayOffice_Name());
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("count", bundle);
                context.startActivity(intent);
            }
        });
        helper.getView(R.id.call_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = item.getPayOffice_Phone() + "";
                if (RegexUtils.isMobileSimple(phone) ||
                        RegexUtils.isTel(phone)) {
                    PhoneUtils.dial(phone);
                } else {
                    ToastUtils.showShort("手机号错误");
                }
            }
        });
    }

}
