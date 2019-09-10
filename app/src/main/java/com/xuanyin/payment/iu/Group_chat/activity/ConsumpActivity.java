package com.xuanyin.payment.iu.Group_chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.Group_chat.adapter.ConsumpAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsumpActivity extends BaseActivity {
    @BindView(R.id.rela)
    RecyclerView rela;
    ConsumpAdapter adapter;
    private boolean isReturn = false;

    @Override
    protected Activity getContext() {
        return ConsumpActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_consump;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        rela.setLayoutManager(new LinearLayoutManager(this));
        rela.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rela.setNestedScrollingEnabled(false);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        adapter = new ConsumpAdapter(NoteActivity.GDs);
        rela.setAdapter(adapter);

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

    @OnClick({R.id.text_cancel, R.id.text_establish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_cancel:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.text_establish:
                for (int i = 0; i < rela.getChildCount(); i++) {
                    LinearLayout layout = (LinearLayout) rela.getChildAt(i);// 获得子item的layout
                    EditText et = layout.findViewById(R.id.consumption_money);
                    String edit = et.getText().toString();
                    if (TextUtils.isEmpty(edit)) {
                        edit = "0.00";
                    }
                    if (!NoteActivity.GDs.get(i).getMoney().equals(edit)) {
                        NoteActivity.GDs.get(i).setMoney(edit);
                        isReturn = true;
                    }
                }
                if (isReturn) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    AppManager.getAppManager().finishActivity();
                    isReturn = false;

                }

                break;
        }
    }
}
