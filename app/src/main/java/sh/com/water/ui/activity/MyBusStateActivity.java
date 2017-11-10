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
import sh.com.water.ui.fragment.ComplaintFragment;
import sh.com.water.ui.fragment.IllegalFragment;

public class MyBusStateActivity extends BaseActivity {

    @BindView(R.id.page)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    NavigationTabStrip tabs;
    private List<Fragment> mList;//view数组
    private NoticPagerAdapter mAdapter;//view数组
    private IllegalFragment illegalFragment;
    private ComplaintFragment complaintFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        setTitle("业务申请进度");
        initData();
    }

    private void initData() {
        illegalFragment = new IllegalFragment();
        complaintFragment = new ComplaintFragment();
        fragmentManager = getSupportFragmentManager();
        mList = new ArrayList<>();// 将要分页显示的View装入数组中
        mList.add(illegalFragment);
        mList.add(complaintFragment);
        mAdapter = new NoticPagerAdapter(fragmentManager, mList);
        viewPager.setAdapter(mAdapter);
        tabs.setTitles("违章举报", "投诉建议");
        tabs.setViewPager(viewPager);
    }
}

