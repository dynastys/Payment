package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;

public class RegisterActivity extends BaseActivity {
    @Override
    protected Activity getContext() {
        return RegisterActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }
}
