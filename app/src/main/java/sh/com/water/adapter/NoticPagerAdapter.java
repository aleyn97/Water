package sh.com.water.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class NoticPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public NoticPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }


    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

}
