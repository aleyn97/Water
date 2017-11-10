package sh.com.water.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.adapter.CommonAdapter;
import sh.com.water.adapter.ViewHolder;
import sh.com.water.bean.IllegaDetilseBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;

public class IllegaDetilseActivity extends BaseActivity {

    @BindView(R.id.zanWu)
    TextView zanWu;
    @BindView(R.id.handel_name)
    TextView handelName;
    @BindView(R.id.hangdel_state)
    TextView hangdelState;
    @BindView(R.id.handel_count)
    TextView handelCount;
    @BindView(R.id.handel_sub_time)
    TextView handelSubTime;
    @BindView(R.id.handel_time)
    TextView handelTime;
    @BindView(R.id.handel_phone)
    TextView handelPhone;
    @BindView(R.id.handel_address)
    TextView handelAddress;
    @BindView(R.id.handel_gridView)
    GridView handelGridView;
    @BindView(R.id.Liner_Not_Det)
    LinearLayout LinerNotDet;
    private List<String> mList = new ArrayList<>();
    private IllegaDetilseBean detilseBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illega_detilse);
        ButterKnife.bind(this);
        setTitle("处理详情");
        Intent intent = getIntent();
        request(intent.getStringExtra("id"));
    }

    private void request(String id) {
        OkHttpUtils
                .get(ServerConfig.ILLEGA_LIST_DETILES)
                .params("violationID", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        detilseBean = JsonMananger.jsonToBean(s, IllegaDetilseBean.class);
                        mList.add(detilseBean.getFILE_PATH0());
                        mList.add(detilseBean.getFILE_PATH1());
                        mList.add(detilseBean.getFILE_PATH2());
                        mList.add(detilseBean.getFILE_PATH3());
                        initDate();
                    }
                });
    }

    private void initDate() {
        handelName.setText(detilseBean.getDispose_Name());
        handelPhone.setText(detilseBean.getViolation_Phone());
        handelAddress.setText(detilseBean.getViolation_Site());
        handelSubTime.setText(detilseBean.getViolation_Time() + "");
        handelTime.setText(detilseBean.getDispose_Time() + "");
        hangdelState.setText(detilseBean.getDispose_Content());
        handelCount.setText(detilseBean.getViolation_Content());
        handelGridView.setAdapter(new CommonAdapter<String>(IllegaDetilseActivity.this, mList, R.layout.adapter_photo) {
            @Override
            public void convert(ViewHolder helper, String item) {
//                helper.setImageResource(R.id.imageView1, );
                Picasso.with(IllegaDetilseActivity.this).load(item).into((ImageView) helper.getView(R.id.imageView1));
            }
        });
    }
}
