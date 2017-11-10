package sh.com.water.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.adapter.MainButAdapter;
import sh.com.water.adapter.MainNoticAdapter;
import sh.com.water.bean.StopWaterNoticBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.ui.MyApplication;
import sh.com.water.utils.GlideImageLoader;
import sh.com.water.utils.JsonMananger;

public class MainActivity extends BaseActivity {
    @BindView(R.id.mPtrFrame)
    PtrFrameLayout mPtrFrame;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.main_list)
    ListView mainList;
    private MainButAdapter mAdapter;
    List<Integer> Listimage = new ArrayList<>();
    private Intent intent;
    private List<StopWaterNoticBean.NoticeInfoBean> mList = new ArrayList<>();
    private StopWaterNoticBean sysmentNoticBean;
    private MainNoticAdapter mListAdapter;
    private long mExitTime = 0;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDate();
        initListDate();
        initView();
        mListAdapter = new MainNoticAdapter(this, mList);
        mainList.setAdapter(mListAdapter);
    }

    private void initListDate() {
        OkHttpUtils
                .get(ServerConfig.STOP_NOTIC)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        sysmentNoticBean = JsonMananger.jsonToBean(s, StopWaterNoticBean.class);
                        mList.addAll(sysmentNoticBean.getNoticeInfo());
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    private void initDate() {
        app = (MyApplication) getApplication();
        int[] imageId = new int[]{R.drawable.shape_but_fen, R.drawable.shape_but_green,
                R.drawable.shape_but_huang, R.drawable.shape_but_zi,
                R.drawable.shape_but_huang, R.drawable.shape_but_green,
                R.drawable.shape_but_blue, R.drawable.shape_but_fen};
        int[] imageSrc = new int[]{R.drawable.svg_main_query, R.drawable.svg_main_fault,
                R.drawable.svg_main_jubao, R.drawable.svg_main_jianyi,
                R.drawable.svg_main__map, R.drawable.svg_main_zhinan,
                R.drawable.svg_main_waterbaike, R.drawable.svg_main_bus};
        // 设置标题
        String[] title = new String[]{getString(R.string.QueryPay), getString(R.string.FaultRepair), getString(R.string.IllegalReport), getString(R.string.Compla),
                getString(R.string.Busout), getString(R.string.BusGuide), getString(R.string.wate_baike), getString(R.string.BusManager)};
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < title.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            map.put("src", imageSrc[i]);
            listmap.add(map);
        }
        Listimage.add(R.mipmap.banner_one);
        Listimage.add(R.mipmap.banner_two);
        Listimage.add(R.mipmap.banner_three);
        mAdapter = new MainButAdapter(this, listmap);
        gridView.setAdapter(mAdapter);
    }

    private void initView() {
        setTitle(getString(R.string.app_name));
        btn_left.setVisibility(View.GONE);
        btn_right.setBackgroundResource(R.drawable.ic_admin);
        btn_right.setVisibility(View.VISIBLE);
        setRightOnClick(PresonCenterActivity.class);
        //头部风格
        final MaterialHeader header = new MaterialHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);//显示相关工具类，用于获取用户屏幕宽度、高度以及屏幕密度。同时提供了dp和px的转化方法。
        mPtrFrame.setHeaderView(header);
//        mPtrFrame.setPinContent(true);//刷新时，保持内容不动，仅头部下移,默认,false
        mPtrFrame.addPtrUIHandler(header);
//        mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        //banner配置
        mPtrFrame.setPtrHandler(new PtrHandler() {
            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mList.clear();
                initListDate();
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListAdapter.notifyDataSetChanged();
                        mPtrFrame.refreshComplete();
//                        mPtrFrame.autoRefresh();//自动刷新
                    }
                }, 1800);

            }

            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                // return true;
            }
        });
        banner.setImageLoader(new GlideImageLoader())
                .setImages(Listimage)
                .setBannerAnimation(Transformer.Accordion)
                .start();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Onclick(i);
            }
        });
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, StopWaterActivity.class);
                intent.putExtra("id", mList.get(i).getId() + "");
                startActivity(intent);
            }
        });
    }

    private void Onclick(int i) {
        switch (i) {
            case 0:
                startActivity(QueryPayActivity.class);
                break;
            case 1:
                startActivity(FaultActivity.class);
                break;
            case 2:
                LoginIF(IllegaReportActivity.class);
                break;
            case 3:
                LoginIF(ComplaActivity.class);
                break;
            case 4:
                startActivity(BusoutActivity.class);
                break;
            case 5:
                startActivity(BusGuideActivity.class);
                break;
            case 6:
                startActivity(WaterbaikeActivity.class);
                break;
            case 7:
                showNormalDialog();
                break;
        }
    }

    private void LoginIF(Class<?> cls) {
        if (!app.getUsername().equals("")) {
            startActivity(cls);
        } else {
            ToastUtils.showShort("您还未登录，不能进行该操作！");
        }
    }

    private void showNormalDialog() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
        normalDialog.setMessage("该功能暂时未开放");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.show();
    }

    @OnClick(R.id.notice_bar)
    public void onViewClicked() {
        startActivity(NoticeActivity.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //1.点击返回键条件成立
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            //2.点击的时间差如果大于2000，则提示用户点击两次退出
            if (System.currentTimeMillis() - mExitTime > 2000) {
                //3.保存当前时间
                mExitTime = System.currentTimeMillis();
                //4.提示
                ToastUtils.showShort("再按一次退出应用");
            } else {
                //5.点击的时间差小于2000，退出。
                finish();
                System.exit(0);
            }
            return true;
        }
        return false;
    }

}
