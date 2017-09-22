package sh.com.water.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sh.com.water.R;
import sh.com.water.adapter.NoticPagerAdapter;
import sh.com.water.common.BaseActivity;
import sh.com.water.ui.fragment.StopNoticFragment;
import sh.com.water.ui.fragment.WaterbusFragment;

public class NoticeActivity extends BaseActivity {

    @BindView(R.id.page)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    NavigationTabStrip tabs;
    private List<Fragment> mList;//view数组
    private NoticPagerAdapter mAdapter;//view数组
    private StopNoticFragment stopFragment;
    private WaterbusFragment waterFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        setTitle(getString(R.string.Notice));
        initData();
    }

    private void initData() {
        stopFragment = new StopNoticFragment();
        waterFragment = new WaterbusFragment();
        fragmentManager = getSupportFragmentManager();
        mList = new ArrayList<>();// 将要分页显示的View装入数组中
        mList.add(stopFragment);
        mList.add(waterFragment);
        mAdapter = new NoticPagerAdapter(fragmentManager, mList);
        viewPager.setAdapter(mAdapter);
        tabs.setTitles(getString(R.string.Stop_water_notic), getString(R.string.water_presen));
        tabs.setViewPager(viewPager);
    }
}

