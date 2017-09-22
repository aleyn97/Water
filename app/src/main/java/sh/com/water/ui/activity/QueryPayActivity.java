package sh.com.water.ui.activity;

import android.os.Bundle;

import sh.com.water.R;
import sh.com.water.common.BaseActivity;

public class QueryPayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_pay);
        setTitle(getString(R.string.QueryPay));
    }
}
