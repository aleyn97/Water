package sh.com.water.adapter;

import android.content.Context;

import java.util.List;

import sh.com.water.R;
import sh.com.water.bean.StopWaterNoticBean;

/**
 * Created by Administrator on 2017/10/8.
 */

public class StopWaterNoticAdapter extends CommonAdapter<StopWaterNoticBean.NoticeInfoBean> {
    private Context context;
    private List<StopWaterNoticBean.NoticeInfoBean> mData;
    private int itemLayoutId;

    public StopWaterNoticAdapter(Context mContext, List<StopWaterNoticBean.NoticeInfoBean> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
        this.context = mContext;
        this.mData = mDatas;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public void convert(ViewHolder helper, StopWaterNoticBean.NoticeInfoBean item) {
        helper.setImageBackgroundResource(R.id.img_text, R.mipmap.app_logo);
        helper.setText(R.id.tv_text, item.getNotice_title());
    }
}
