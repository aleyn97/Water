package sh.com.water.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import sh.com.water.R;
import sh.com.water.adapter.MainButAdapter;
import sh.com.water.common.BaseActivity;
import sh.com.water.utils.GlideImageLoader;

public class MainActivity extends BaseActivity {
    @BindView(R.id.mPtrFrame)
    PtrFrameLayout mPtrFrame;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.banner)
    Banner banner;
    private MainButAdapter mAdapter;
    List<Integer> Listimage = new ArrayList<Integer>();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDate();
        initView();
    }

    private void initDate() {
        int[] imageId = new int[]{R.drawable.shape_but_fen, R.drawable.shape_but_blue,
                R.drawable.shape_but_green, R.drawable.shape_but_huang,
                R.drawable.shape_but_zi, R.drawable.shape_but_zi,
                R.drawable.shape_but_huang, R.drawable.shape_but_green,
                R.drawable.shape_but_blue, R.drawable.shape_but_fen};
        int[] imageSrc = new int[]{R.drawable.svg_main_query, R.drawable.svg_main_meg,
                R.drawable.svg_main_fault, R.drawable.svg_main_jubao,
                R.drawable.svg_main_jianyi, R.drawable.svg_main_news,
                R.drawable.svg_main__map, R.drawable.svg_main_zhinan,
                R.drawable.svg_main_waterbaike, R.drawable.svg_main_bus};
        // 设置标题
        String[] title = new String[]{getString(R.string.QueryPay), getString(R.string.Notice), getString(R.string.FaultRepair), getString(R.string.IllegalReport), getString(R.string.Compla), getString(R.string.BusNews),
                getString(R.string.Busout), getString(R.string.BusGuide), getString(R.string.wate_baike), getString(R.string.BusManager)};
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < title.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            map.put("src", imageSrc[i]);
            listmap.add(map);
        }
        Listimage.add(R.mipmap.one);
        Listimage.add(R.mipmap.two);
        Listimage.add(R.mipmap.three);
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
                System.out.println("MainActivity.onRefreshBegin");
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                        //mPtrFrame.autoRefresh();//自动刷新
                    }
                }, 1800);

            }

            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                System.out.println("MainActivity.checkCanDoRefresh");
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
    }

    private void Onclick(int i) {
        switch (i) {
            case 0:
                TiaoZhuan(QueryPayActivity.class);
                break;
            case 1:
                TiaoZhuan(NoticeActivity.class);
                break;
            case 2:
                TiaoZhuan(FaultActivity.class);
                break;
            case 3:
                TiaoZhuan(IllegaReportActivity.class);
                break;
            case 4:
                TiaoZhuan(ComplaActivity.class);
                break;
            case 5:
                TiaoZhuan(BusNewsActivity.class);
                break;
            case 6:
                TiaoZhuan(BusoutActivity.class);
                break;
            case 7:
                TiaoZhuan(BusGuideActivity.class);
                break;
            case 8:
                TiaoZhuan(WaterbaikeActivity.class);
                break;
            case 9:
                showNormalDialog();
                break;
        }
    }

    public void TiaoZhuan(Class<?> cls) {
        intent = new Intent(this, cls);
        startActivity(intent);
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
}
