package com.xuanyin.payment.iu.activity.huodong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.recyc)
    RecyclerView recyc;

    @Override
    protected Activity getContext() {
        return DetailsActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_details;
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
        recyc.setLayoutManager(new LinearLayoutManager(this));
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

    @OnClick({R.id.make_note, R.id.settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.make_note:
                break;
            case R.id.settlement:
                break;
        }
    }
}
