package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuanyin.User;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.AddressBookAdapter;
import com.xuanyin.payment.iu.entity.PhoneDto;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.utils.PhoneUtil;
import com.xuanyin.payment.view.ILogView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressBookActivity extends BaseActivity implements ILogView {

    @BindView(R.id.recyc)
    RecyclerView recyc;
    private Presenter presenter;
    AddressBookAdapter adapter;

    @Override
    protected Activity getContext() {
        return AddressBookActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_addressbook;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        presenter = new Presenter(this);
        recyc.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        presenter.information();
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void show() {
        adapter = new AddressBookAdapter(presenter.util.dataAddressBook(getContext()));
        recyc.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
