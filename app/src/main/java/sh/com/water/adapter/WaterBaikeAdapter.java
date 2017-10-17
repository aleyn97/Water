package sh.com.water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sh.com.water.R;
import sh.com.water.bean.WaterBaiKeBean;

/**
 * Created by Administrator on 2017/9/30.
 */

public class WaterBaikeAdapter extends BaseAdapter {
    private List<WaterBaiKeBean.WikiInfoBean> mList;
    private LayoutInflater inflater;
    private Context context;

    public WaterBaikeAdapter(List<WaterBaiKeBean.WikiInfoBean> mList, Context context) {
        this.mList = mList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = inflater.inflate(R.layout.adapter_fold_outside, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tvGroupName.setText(mList.get(i).getWiki_title());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_group_name)
        TextView tvGroupName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
