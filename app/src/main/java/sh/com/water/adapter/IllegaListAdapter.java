package sh.com.water.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import sh.com.water.R;
import sh.com.water.bean.IllegaListBean;
import sh.com.water.bean.StopWaterNoticBean;
import sh.com.water.utils.DatetoStringFormt;

/**
 * Created by Administrator on 2017/10/26.
 */

public class IllegaListAdapter extends CommonAdapter<IllegaListBean> {

    private Context context;
    private List<IllegaListBean> mList = new ArrayList<>();
    private int LayoutId;

    public IllegaListAdapter(Context mContext, List<IllegaListBean> mData, int itemLayoutId1) {
        super(mContext, mData, itemLayoutId1);
        this.context = mContext;
        this.mList = mData;
        this.LayoutId = itemLayoutId1;
    }

    @Override
    public void convert(ViewHolder helper, IllegaListBean item) {
        if (item.getViolation_OK() == 0) {
            helper.setText(R.id.Illega_state, "未处理");
        } else {
            helper.setText(R.id.Illega_state, "已处理");
        }
        helper.setText(R.id.Illega_time, DatetoStringFormt.StringToStrLong(item.getViolation_Time() + ""));
    }

}
