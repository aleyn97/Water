package sh.com.water.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import sh.com.water.R;

/**
 * Created by Administrator on 2017/9/16.
 */

public class MainButAdapter extends BaseAdapter {

    private Context mContext;
    private List<Map<String, Object>> list;
    private LayoutInflater mInflater;

    public MainButAdapter(Context mContext, List<Map<String, Object>> list) {
        this.mContext = mContext;
        this.list = list;
        this.mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = mInflater.inflate(R.layout.adapter_main_item, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        Map<String, Object> map = list.get(i);
//        vh.ItemImage.setImageResource(R.drawable.ic_admin);
        vh.ItemImage.setBackgroundResource((Integer) map.get("image"));
        vh.src.setImageResource((Integer) map.get("src"));
        vh.ItemText.setText(map.get("title") + "");
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.ItemImage)
        ImageView ItemImage;
        @BindView(R.id.ItemText)
        TextView ItemText;
        @BindView(R.id.src)
        ImageView src;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
