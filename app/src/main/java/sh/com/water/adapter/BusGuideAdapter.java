package sh.com.water.adapter;

import android.content.Context;

import java.util.List;

import sh.com.water.R;
import sh.com.water.bean.BusGuideBean;

/**
 * Created by Administrator on 2017/9/30.
 */

public class BusGuideAdapter extends CommonAdapter<BusGuideBean.GuideInfoBean> {

    public BusGuideAdapter(Context mContext, List<BusGuideBean.GuideInfoBean> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, BusGuideBean.GuideInfoBean item) {
        helper.setText(R.id.tv_group_name, item.getGuide_title());
    }
}
