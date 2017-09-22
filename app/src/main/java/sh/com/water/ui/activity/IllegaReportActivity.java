package sh.com.water.ui.activity;

import android.os.Bundle;

import sh.com.water.R;
import sh.com.water.common.BaseActivity;

public class IllegaReportActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illega_report);
        setTitle(getString(R.string.IllegalReport));
    }
}
