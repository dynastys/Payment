package com.xuanyin.payment.iu.Group_chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.utils.All;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeGroupNameActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.edit)
    EditText edit;

    @Override
    protected Activity getContext() {
        return ChangeGroupNameActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_change;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        setSupportActionBar(toolBar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        edit.setText(All.GroupName);
        setSelectionEnd(edit);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.tijiao)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tijiao:


                AppManager.getAppManager().finishActivity();
                break;
        }
    }

    public static void setSelectionEnd(EditText editText) {
        Editable b = editText.getText();
        editText.setSelection(b.length());
    }
}
