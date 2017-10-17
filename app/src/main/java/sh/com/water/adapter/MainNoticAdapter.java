package sh.com.water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sh.com.water.R;
import sh.com.water.bean.StopWaterNoticBean;

/**
 * Created by Administrator on 2017/10/12.
 */

public class MainNoticAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<StopWaterNoticBean.NoticeInfoBean> mList = new ArrayList<>();
    private Context mContext;

    public MainNoticAdapter(Context context, List<StopWaterNoticBean.NoticeInfoBean> mList) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (mList.size() > 2) {
            return 2;
        }
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
            view = mLayoutInflater.inflate(R.layout.adapter_text, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tvText.setText(mList.get(i).getNotice_title());
        vh.imgText.setImageResource(R.mipmap.app_logo);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.img_text)
        ImageView imgText;
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
