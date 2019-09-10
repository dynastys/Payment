package com.xuanyin.payment.iu.Group_chat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xuanyin.People;
import com.xuanyin.User;
import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.DetailsAdapter;
import com.xuanyin.payment.iu.Group_chat.adapter.MemberAdapter;
import com.xuanyin.payment.iu.entity.Member;
import com.xuanyin.payment.utils.All;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberActivity extends BaseActivity {
    @BindView(R.id.member)
    RecyclerView member;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.invitation)
    TextView invitation;

    private List<Member> members = new ArrayList<>();

    private MemberAdapter memadapter;
    List<User> user;
    List<People> peoples;
    @Override
    protected Activity getContext() {
        return MemberActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_member;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        member.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

        if (getIntent().getStringExtra("Come").equals("群聊成员")) {
            setSupportActionBar(toolBar);
            //设置是否有返回箭头
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolText.setText("群聊成员");
            invitation.setVisibility(View.GONE);

            DetailsAdapter adapter = new DetailsAdapter(getContext(),GroupChatDetailsActivity.list,true);
            member.setAdapter(adapter);

        } else if (getIntent().getStringExtra("Come").equals("邀请新成员")) {
            cancel.setVisibility(View.VISIBLE);
            toolText.setText("邀请新成员");

            user = LitePal.findAll(User.class);
            peoples = LitePal.findAll(People.class);

            for (int i = 0; i < peoples.size(); i++) {
                if (peoples.get(i).getGroupname().equals(All.GroupName)){
                    for (int j = 0; j < user.size(); j++) {
                        if ((user.get(j).getName()).equals(peoples.get(i).getName())){
                            user.remove(j);
                        }
                    }
                }
            }

            for (int i = 0; i < user.size(); i++) {
                Member member = new Member();
                member.setName(user.get(i).getName());
                member.setImage(user.get(i).getBitmap());
                members.add(member);
            }


            memadapter = new MemberAdapter(getContext(), members);
            member.setAdapter(memadapter);

        }

//        memadapter.setCheck(new Check() {
//            @Override
//            public void onCheck(List<Boolean> list) {
//                for (int i = 0; i < list.size(); i++) {
//                    Log.i("TAGAAA",list.get(i)+"");
//                }
//            }
//        });

    }

    @Override
    protected void setListener() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //Toolbar的事件---返回
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.invitation)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.invitation:


                for (int i = 0; i < memadapter.ischeck.size(); i++) {
                    if (memadapter.ischeck.get(i)){
                        People people = new People();
                        people.setGroupname(All.GroupName);
                        people.setName(members.get(i).getName());
                        people.setImage(members.get(i).getImage());
                        people.setMoney("0.00");
                        people.setDrawee(false);
                        people.setParticipants(true);
                        people.save();
                    }
                }
                AppManager.getAppManager().finishActivity();
//                memadapter.setCheck(new Check() {
//                    @Override
//                    public void onCheck(List<Boolean> list) {
//                        for (int i = 0; i < list.size(); i++) {
//                            if (!list.get(i)){
//                                Log.i("TAGAAA",users.get(i).getName());
//                            }
//                        }
//                    }
//                });
        }
    }
}
