package com.xuanyin.payment.iu.Group_chat.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.MultipleItemQuickAdapter;
import com.xuanyin.payment.iu.entity.AddressBook;
import com.xuanyin.payment.iu.entity.MultipleItem;
import com.xuanyin.payment.iu.entity.Team;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.utils.All;
import com.xuanyin.payment.view.ILogView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity implements ILogView {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.news_recyc)
    RecyclerView newsRecyc;
    private String title;
    private Presenter presenter;
    //private GroupRecyclerAdapter<Team, TeamAdapter, MemberAdapter> groupAdapter;
    private List<MultipleItem> multipleItems = new ArrayList<>();
    private List<AddressBook> s = new ArrayList<>();
    private MultipleItemQuickAdapter adapter;

    @Override
    protected Activity getContext() {
        return NewsActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_news;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        title = getIntent().getStringExtra("GroupChatName");
        All.GroupName = title;
        setSupportActionBar(toolBar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager layout = new LinearLayoutManager(this);
//        layout.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
//        layout.setReverseLayout(true);//列表翻转
        newsRecyc.setLayoutManager(layout);
        presenter = new Presenter(this);
        newsRecyc.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Add");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);

        toolText.setText(title);
        presenter.information();
        multipleItems = presenter.util.getnews();
        adapter = new MultipleItemQuickAdapter(getContext(),multipleItems);
        newsRecyc.setAdapter(adapter);

    }

    // broadcast receiver
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("Add")) {

                multipleItems.clear();
                multipleItems = presenter.util.getnews();
                final MultipleItemQuickAdapter adapter = new MultipleItemQuickAdapter(getContext(), multipleItems);
                newsRecyc.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void setListener() {
//        groupAdapter.setOnChildClickListener(new OnChildClickListener() {
//            @Override
//            public void onChildClick(View itemView, int groupPosition, int childPosition) {
//                Log.i("TAGAAA", groupPosition + "");
//                Log.i("TAGAAA", childPosition + "");
//                NewsDetailsActivity();
//            }
//        });
//        final MultipleItemQuickAdapter adapter = new MultipleItemQuickAdapter(getContext(),s);
//        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
//              return s.get(position).getType();
//            }
//        });
//        newsRecyc.setAdapter(adapter);
//        Log.i("TAGAAA",s.size()+"");
//        ab = new AdapterAB(s);
//        newsRecyc.setAdapter(ab);
    }

    @Override
    public void show() {
//        presenter.util.book().size();
//        for (int i = 0; i < presenter.util.book().size(); i++) {
//          Log.i("TAGAAA","测试  "+presenter.util.book().get(i).getName());
//          Log.i("TAGAAA","测试  "+presenter.util.book().get(i).getPhone());
//        }
//        Log.i("TAGAAA",presenter.util.book().size()+"");
//        ab = new AdapterAB(presenter.util.book());
//        newsRecyc.setAdapter(ab);

        //s = presenter.util.book();

//        GroupingList(presenter.util.dataNews());
//        newsRecyc.setAdapter(groupAdapter);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void GroupChatDetailsActivity() {

        intentActivity(GroupChatDetailsActivity.class);
    }

    private void NoteActivity() {
        intentActivity(NoteActivity.class);
    }


    @OnClick({R.id.img_details, R.id.make_note, R.id.settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_details:
                GroupChatDetailsActivity();
                break;
            case R.id.make_note:
                NoteActivity();
                break;
            case R.id.settlement:

                break;
        }
    }

    private void GroupingList(List<Team> list) {
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
//        groupAdapter = new GroupRecyclerAdapter<Team, TeamAdapter, MemberAdapter>(list) {
//            @Override
//            protected TeamAdapter onCreateGroupViewHolder(ViewGroup parent) {
//                return new TeamAdapter(layoutInflater.inflate(R.layout.adapter_team_title, parent, false));
//            }
//
//            @Override
//            protected MemberAdapter onCreateChildViewHolder(ViewGroup parent) {
//                return new MemberAdapter(layoutInflater.inflate(R.layout.adapter_team_member, parent, false));
//
//            }
//
//            @Override
//            protected void onBindGroupViewHolder(TeamAdapter holder, int groupPosition) {
//
//            }
//
//            @Override
//            protected void onBindChildViewHolder(MemberAdapter holder, int groupPosition, int childPosition) {
//
//            }
//
//            @Override
//            protected int getChildCount(Team group) {
//                return group.members.size();
//            }
    }

    ;
    //GroupItemDecoration decoration = new GroupItemDecoration(groupAdapter);
    //decoration.setGroupDivider(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_height_16_dp, null));
//        decoration.setTitleDivider(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_height_1_px, null));
//        decoration.setChildDivider(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_white_header, null));
//        newsRecyc.addItemDecoration(decoration);
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRefreshBroadcastReceiver);
    }
}
