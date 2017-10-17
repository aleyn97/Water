package sh.com.water.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sh.com.water.R;
import sh.com.water.common.BaseActivity;
import sh.com.water.utils.DatetoStringFormt;

public class OrderQueryActivity extends BaseActivity {

    @BindView(R.id.star_time)
    TextView starTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.star)
    TextView star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);
        ButterKnife.bind(this);
        setTitle("缴费记录");
        endTime.setText(DatetoStringFormt.getDateShort(TimeUtils.getNowDate()));
    }

    protected void Time(final TextView view) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(2000, 0, 1);
        endDate.set(2050, 11, 31);
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (IfTime(view, date)) {
                    ToastUtils.showShort("暂无记录");
                } else {
                    ToastUtils.showShort("时间选择有误");
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setRangDate(startDate, endDate)
                .build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }

    @OnClick({R.id.star_time, R.id.end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.star_time:
                Time(starTime);
                break;
            case R.id.end_time:
                Time(endTime);
                break;
        }
    }

    protected boolean IfTime(TextView view, Date date) {
        long lg1 = TimeUtils.getNowMills() - TimeUtils.date2Millis(date);
        long time1 = TimeUtils.date2Millis(date);
        if (lg1 > 0) {
            if (view == starTime) {
                long lg = time1 -
                        TimeUtils.date2Millis(DatetoStringFormt.strToDateLong(endTime.getText().toString()));
                if (lg >= 0) {
                    return false;
                } else {
                    view.setText(DatetoStringFormt.getDateShort(date));
                    return true;
                }
            }
            if (view == endTime) {
                long lg = time1 -
                        TimeUtils.date2Millis(DatetoStringFormt.strToDateLong(starTime.getText().toString()));
                if (lg <= 0) {
                    return false;
                } else {
                    view.setText(DatetoStringFormt.getDateShort(date));
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }
}
