package com.xuanyin.payment.iu.activity.huodong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xuanyin.ConsumptionDetails;
import com.xuanyin.Consumptions;
import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.Group_chat.activity.GroupChatDetailsActivity;
import com.xuanyin.payment.iu.activity.huodong.adapter.Invitation_of_MembersAdapter;
import com.xuanyin.payment.iu.entity.Consumption;
import com.xuanyin.payment.iu.entity.Member;
import com.xuanyin.payment.utils.All;
import com.xuanyin.payment.view.ILogView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends BaseActivity implements ILogView {
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.add_recyc)
    RecyclerView addRecyc;

    private SimpleDateFormat simpleDateFormat;
    private Date date;
    private Invitation_of_MembersAdapter adapter;
    private List<Member> list = new ArrayList<>();
    @Override
    protected Activity getContext() {
        return AddActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_adds;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        addRecyc.setLayoutManager(new LinearLayoutManager(this));
        addRecyc.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        list.clear();
        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setImage(R.mipmap.nice);
            member.setName("lilili");
            list.add(member);
        }

        adapter = new Invitation_of_MembersAdapter(getContext(),list);
        addRecyc.setAdapter(adapter);
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

    @OnClick({R.id.btn_cancel, R.id.invitation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                AppManager.getAppManager().finishActivity();
                //overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
                break;
            case R.id.invitation:
                if (!TextUtils.isEmpty(editName.getText().toString())){
                    simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                    //获取当前时间
                    date = new Date(System.currentTimeMillis());

                    String TIME = simpleDateFormat.format(date);
                    Consumptions consumptions = new Consumptions();
                    consumptions.setType(2);
                    consumptions.setGroup_name(All.GroupName);
                    consumptions.setData(TIME);
                    consumptions.setMoney("0");
                    consumptions.save();
                    ConsumptionDetails details = new ConsumptionDetails();
                    details.setGroupname(All.GroupName);
                    details.setTime(TIME);
                    details.setName(editName.getText().toString());
                    details.save();
                    AppManager.getAppManager().finishActivity();
                    AppManager.finishActivity(GroupChatDetailsActivity.class);
                    // 广播通知
                    Intent intent = new Intent();
                    intent.setAction("Add");
                    sendBroadcast(intent);
                }
                break;
        }
    }
}
