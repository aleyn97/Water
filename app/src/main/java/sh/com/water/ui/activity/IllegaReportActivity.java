package sh.com.water.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import sh.com.water.R;
import sh.com.water.adapter.PhotoAdapter;
import sh.com.water.common.BaseActivity;
import sh.com.water.common.ServerConfig;
import sh.com.water.ui.MyApplication;
import sh.com.water.utils.ClearEditText;
import sh.com.water.utils.ImageDeal;
import sh.com.water.utils.LoadingDialog;

public class IllegaReportActivity extends BaseActivity {

    public static final int IMAGE_PICKER = 1004;
    @BindView(R.id.jubao_name)
    ClearEditText name;
    @BindView(R.id.jubao_address)
    ClearEditText address;
    @BindView(R.id.jubao_input_explain)
    ClearEditText count;
    @BindView(R.id.pic_gridView)
    GridView gridView;
    MyApplication app;

    private PhotoAdapter adapter;
    private List<ClearEditText> edList = new ArrayList<>();
    private List<ImageItem> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illega_report);
        ButterKnife.bind(this);
        setTitle(getString(R.string.IllegalReport));
        initData();
    }

    private void initData() {
        app = (MyApplication) getApplication();
        adapter = new PhotoAdapter(mList, this, gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mList.size()) {
                    if (mList.size() == 4) {
                        ToastUtils.showShort("最多添加四张");
                    } else {
                        Intent intent = new Intent(IllegaReportActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, IMAGE_PICKER);
                    }
                } else {
                    dialog(i);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                mList.addAll((ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS));
                adapter.notifyDataSetChanged();
            } else {
                ToastUtils.showShort("没有数据");
            }
        }
    }

    private void sumbit() {
        if (IfNull() == null) {
            if (mList.size() > 0) {
                Map<String, String> map = new HashMap<>();
                map.put("ViolationName", name.getText().toString());
                map.put("ViolationPhone", app.getUsername());
                map.put("ViolationSite", address.getText().toString());
                map.put("ViolationContent", count.getText().toString());
                String Pic = "";
                for (int i = 0; i < mList.size(); i++) {
                    try {
                        String photo = ImageDeal.encodeBase64File(mList.get(i).path);
                        if (Pic.equals("")) {
                            Pic = photo;
                        } else {
                            Pic = Pic + "," + photo;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                map.put("ViolationPic", Pic);
                JSONObject json = (JSONObject) JSONObject.toJSON(map);
                request(json.toJSONString());
            } else {
                ToastUtils.showShort("图片至少为一张");
            }
        } else {
            IfNull().setBackgroundResource(R.drawable.shape_error_bg);
            IfNull().setShakeAnimation();
            ToastUtils.showShort(IfNull().getHint().toString() + "不能为空");
        }
    }

    public void request(String Json) {
        LoadingDialog.createLoadingDialog(this, "正在提交...");
        OkHttpUtils
                .post(ServerConfig.JU_BAO)
                .upJson(Json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LoadingDialog.closeDialog();
                        ResultDialog(JSONObject.parseObject(s).getString("msg"));
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        LoadingDialog.closeDialog();
                        super.onError(call, response, e);
                        ToastUtils.showShort("服务器或网络异常");
                    }
                });
    }

    public ClearEditText IfNull() {
        edList.add(name);
        edList.add(address);
        edList.add(count);
        for (int i = 0; i < edList.size(); i++) {
            if (edList.get(i).getText().toString().equals("")) return edList.get(i);
        }
        return null;
    }

    @OnClick(R.id.jubao_sub)
    public void onViewClicked() {
        sumbit();
    }

    private void dialog(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mList.remove(i);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void ResultDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("提交结果");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();
    }

}
