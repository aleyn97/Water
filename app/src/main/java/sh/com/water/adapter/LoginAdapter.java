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
 * Created by Administrator on 2017/9/18.
 */

public class LoginAdapter extends BaseAdapter {
    private List<Map<String, Object>> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public LoginAdapter(List<Map<String, Object>> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
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
            view = mInflater.inflate(R.layout.adapter_login_list, null);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        if (i == 5) {
            vh.view.setVisibility(View.VISIBLE);
        }
        vh.img.setImageResource((Integer) mList.get(i).get("image"));
        vh.text.setText(mList.get(i).get("text") + "");
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.end_buju)
        View view;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
