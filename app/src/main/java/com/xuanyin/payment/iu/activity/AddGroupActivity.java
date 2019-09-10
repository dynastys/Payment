package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xuanyin.Demo;
import com.xuanyin.People;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.view.ILogView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddGroupActivity extends BaseActivity implements ILogView {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.btn_cancel)
    TextView btnCancel;
    @BindView(R.id.btn_confirm)
    TextView btnConfirm;
    @BindView(R.id.edit_name)
    EditText editName;

    private SimpleDateFormat simpleDateFormat;
    private Date date;

    @Override
    protected Activity getContext() {
        return AddGroupActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_add;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        //获取当前时间
        date = new Date(System.currentTimeMillis());

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void show() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                finish();
                //overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
                break;
            case R.id.btn_confirm:
                if (!TextUtils.isEmpty(editName.getText())){
                    Demo demo = new Demo();
                    demo.setData(simpleDateFormat.format(date));
                    demo.setGroupname(editName.getText().toString());
                    demo.save();
                    People people = new People();
                    people.setGroupname(editName.getText().toString());
                    people.setName("85");
                    people.setImage(R.mipmap.icon);
                    people.setMoney("0.00");
                    people.setDrawee(true);
                    people.setParticipants(true);
                    people.save();
                    finish();
                }
                break;
        }
    }
}
