package sh.com.water.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sh.com.water.R;
import sh.com.water.bean.ChinaAddressBean;

public class VersionActivity extends AppCompatActivity {
    @BindView(R.id.address_count)
    TextView addressCount;
    private List<String> List1 = new ArrayList<>();
    private List<List<String>> List2 = new ArrayList<>();
    private List<List<List<String>>> List3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        ButterKnife.bind(this);
        AddressDate();
    }

    @OnClick(R.id.bt_text_address)
    public void onViewClicked() {
        OptionsPickerView picView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = List1.get(options1).toString()
                        + List2.get(options1).get(options2).toString()
                        + List3.get(options1).get(options2).get(options3).toString();
                addressCount.setText(tx);
            }
        }).build();
        picView.setPicker(List1, List2, List3);
        picView.show();
    }

    public void AddressDate() {
        XStream xStream = new XStream();
        xStream.processAnnotations(ChinaAddressBean.class);
        ChinaAddressBean chinaAddressBean = null;
        try {
             chinaAddressBean = (ChinaAddressBean) xStream.fromXML(getResources().getAssets().open("LocList.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < chinaAddressBean.getState().size(); i++) {
            List<String> list1 = new ArrayList<>();
            //省
            List1.add(chinaAddressBean.getState().get(i).getName());
            //市
            List<List<String>> list2 = new ArrayList<>();
            for (int j = 0; j < chinaAddressBean.getState().get(i).getCity().size(); j++) {
                List<String> list2_1 = new ArrayList<>();
                List<String> list2_2 = new ArrayList<>();
                list1.add(chinaAddressBean.getState().get(i).getCity().get(j).getName());
                //区
                List<ChinaAddressBean.State.City.Region> address = chinaAddressBean.getState().get(i).getCity().get(j).getRegion();
                if (address != null) {
                    for (int k = 0; k < address.size(); k++) {
                        list2_1.add(address.get(k).getName());
                    }
                    list2.add(list2_1);
                } else {
                    list2_2.add("");
                    list2.add(list2_2);
                }
            }
            List2.add(list1);
            List3.add(list2);
        }

    }
}
