package sh.com.water.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
import sh.com.water.utils.ClearEditText;
import sh.com.water.utils.ImageDeal;
import sh.com.water.utils.LoadingDialog;

public class FaultActivity extends BaseActivity {
    public static final int IMAGE_PICKER = 1004;
    @BindView(R.id.ed_name)
    ClearEditText edName;
    @BindView(R.id.ed_phone)
    ClearEditText edPhone;
    @BindView(R.id.ed_input_explain)
    ClearEditText edInputExplain;
    @BindView(R.id.pic_gridView)
    GridView picGridView;

    private List<ImageItem> mList = new ArrayList<>();
    private List<ClearEditText> edList = new ArrayList<>();
    private PhotoAdapter adapter;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault);
        ButterKnife.bind(this);
        setTitle(getString(R.string.FaultRepair));
        initData();
    }

    private void initData() {
        adapter = new PhotoAdapter(mList, this, picGridView);
        picGridView.setAdapter(adapter);
        picGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mList.size()) {
                    if (mList.size() == 4) {
                        ToastUtils.showShort("最多添加四张");
                    } else {
                        Intent intent = new Intent(FaultActivity.this, ImageGridActivity.class);
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


    @OnClick(R.id.bt_sub)
    public void onViewClicked() {
        if (IfNull() == null) {
            if (mList.size() > 0) {
                Map<String, String> map = new HashMap<>();
                map.put("repairsuser", edName.getText().toString());
                map.put("repairsphone", edPhone.getText().toString());
                map.put("repairscontent", edInputExplain.getText().toString());
                String Pic = "";
                for (int i = 0; i < mList.size(); i++) {
                    if (Pic.equals("")) {
                        Pic = ImageDeal.convertIconToString(BitmapFactory.decodeFile(mList.get(i).path));
                    } else {
                        Pic = Pic + "," + ImageDeal.convertIconToString(BitmapFactory.decodeFile(mList.get(i).path));
                    }
                }
                map.put("repairsPic", Pic);
                json = (JSONObject) JSONObject.toJSON(map);
                request(json.toJSONString());
            } else {
                ToastUtils.showShort("图片至少为一张");
            }
        } else {
            IfNull().setShakeAnimation();
            IfNull().setBackgroundResource(R.drawable.shape_error_bg);
            ToastUtils.showShort(IfNull().getHint().toString() + "不能为空");
        }
    }

    private void request(String Json) {
        LoadingDialog.createLoadingDialog(this, "正在提交");
        OkHttpUtils
                .post(ServerConfig.ADD_FAULT)
                .upJson(Json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LoadingDialog.closeDialog();
                        json = JSONObject.parseObject(s);
                        ToastUtils.showShort(json.getString("msg"));
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        LoadingDialog.closeDialog();
                        ToastUtils.showShort("服务器或网络异常");
                    }
                });
    }

    protected ClearEditText IfNull() {
        edList.add(edName);
        edList.add(edPhone);
        edList.add(edInputExplain);
        for (int i = 0; i < edList.size(); i++) {
            if (edList.get(i).getText().toString().equals("")) return edList.get(i);
        }
        return null;
    }
}
