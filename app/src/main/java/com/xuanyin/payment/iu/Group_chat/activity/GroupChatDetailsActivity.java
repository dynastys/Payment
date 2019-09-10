package com.xuanyin.payment.iu.Group_chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.activity.huodong.activity.AddActivity;
import com.xuanyin.payment.iu.adapter.DetailsAdapter;
import com.xuanyin.payment.iu.entity.GroupChat;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.utils.All;
import com.xuanyin.payment.view.ILogView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupChatDetailsActivity extends BaseActivity implements ILogView {


    @BindView(R.id.details_recyc)
    RecyclerView detailsRecyc;
    @BindView(R.id.transparent_tool_bar)
    Toolbar transparentToolBar;
    @BindView(R.id.img_into)
    ImageView imgInto;
    @BindView(R.id.detel_group)
    TextView detelGroup;
    @BindView(R.id.text_num)
    TextView textNum;
    @BindView(R.id.group_name_text)
    TextView groupNameText;


    private DetailsAdapter adapter;
    private Presenter presenter;
    private List<GroupChat> groupChats = new ArrayList<>();
    public static List<GroupChat.PeopleGC> list = new ArrayList<>();

    @Override
    protected Activity getContext() {
        return GroupChatDetailsActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_gropchat_details;
    }

    @Override
    protected boolean getStatusBar() {
        return true;
    }

    @Override
    protected void loadViewLayout() {
        setSupportActionBar(transparentToolBar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        detailsRecyc.setLayoutManager(layoutManager);
        presenter = new Presenter(this);
        presenter.information();

        groupNameText.setText(All.GroupName);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        list.clear();
        for (int i = 0; i < groupChats.size(); i++) {
            if (All.GroupName.equals(groupChats.get(i).getTitle())) {

                for (int j = 0; j < groupChats.get(i).getPeopleGCS().size(); j++) {
                    GroupChat.PeopleGC gc = new GroupChat.PeopleGC(groupChats.get(i).getPeopleGCS().get(j).getPeopel(), groupChats.get(i).getPeopleGCS().get(j).getImage(),groupChats.get(i).getPeopleGCS().get(j).isDrawee(),groupChats.get(i).getPeopleGCS().get(j).isParticipants());
                    list.add(gc);
                }
            }
        }
        textNum.setText("共" + list.size() + "人");
        adapter = new DetailsAdapter(getContext(), list, false);
        detailsRecyc.setAdapter(adapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void show() {
        groupChats = presenter.util.datagroupChat();
    }

    //Toolbar的事件---返回
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            AppManager.getAppManager().finishActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getAppManager().finishActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.all_rela, R.id.addhuodong, R.id.group_name_line, R.id.detel_group, R.id.line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_rela:
                Intent allmenmber = new Intent(GroupChatDetailsActivity.this, MemberActivity.class);
                allmenmber.putExtra("Come", "群聊成员");
                startActivity(allmenmber);
                break;
            case R.id.addhuodong:
                intentActivity(AddActivity.class);
                break;
            case R.id.group_name_line:
                intentActivity(ChangeGroupNameActivity.class);
                break;
            case R.id.detel_group:
                break;
            case R.id.line:
                Intent addmenmber = new Intent(GroupChatDetailsActivity.this, MemberActivity.class);
                addmenmber.putExtra("Come", "邀请新成员");
                startActivity(addmenmber);
                break;
        }
    }

}
