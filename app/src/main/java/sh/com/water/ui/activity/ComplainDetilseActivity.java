package sh.com.water.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import sh.com.water.bean.ComplainDetilseBean;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.utils.JsonMananger;

public class ComplainDetilseActivity extends BaseActivity {

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
    @BindView(R.id.handel_gridView)
    GridView handelGridView;
    @BindView(R.id.Liner_Not_Det)
    LinearLayout LinerNotDet;
    private List<String> mList = new ArrayList<>();
    private ComplainDetilseBean detilseBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_detilse);
        ButterKnife.bind(this);
        setTitle("处理详情");
        Intent intent = getIntent();
        request(intent.getStringExtra("id"));
    }

    private void request(String id) {
        OkHttpUtils
                .get(ServerConfig.COMPLAINT_LIST_DETILES)
                .params("complaintsID", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        detilseBean = JsonMananger.jsonToBean(s, ComplainDetilseBean.class);
                        mList.add(detilseBean.getFILE_PATH0());
                        mList.add(detilseBean.getFILE_PATH1());
                        mList.add(detilseBean.getFILE_PATH2());
                        mList.add(detilseBean.getFILE_PATH3());
                        initDate();
                    }
                });
    }

    private void initDate() {
        handelName.setText(detilseBean.getAcceptance_Name());
        handelPhone.setText(detilseBean.getComplaints_Phone());
        handelSubTime.setText(detilseBean.getAcceptance_Time() + "");
        handelTime.setText(detilseBean.getComplaints_Time() + "");
        hangdelState.setText(detilseBean.getAcceptance_Content());
        handelCount.setText(detilseBean.getComplaints_Content());
        handelGridView.setAdapter(new CommonAdapter<String>(ComplainDetilseActivity.this, mList, R.layout.adapter_photo) {
            @Override
            public void convert(ViewHolder helper, String item) {
                Picasso.with(ComplainDetilseActivity.this).load(item).into((ImageView) helper.getView(R.id.imageView1));
            }
        });
    }
}
