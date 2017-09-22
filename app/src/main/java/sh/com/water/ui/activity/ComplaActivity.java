package sh.com.water.ui.activity;

import android.os.Bundle;

import sh.com.water.R;
import sh.com.water.common.BaseActivity;

public class ComplaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compla);
        setTitle(getString(R.string.Compla));
    }
}
