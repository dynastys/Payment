package com.xuanyin.payment.iu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuanyin.payment.BaseFragment;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Click;
import com.xuanyin.payment.iu.activity.MainActivity;
import com.xuanyin.payment.iu.Group_chat.activity.NewsActivity;
import com.xuanyin.payment.iu.adapter.GroupChatAdapter;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.view.ILogView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupFragment extends BaseFragment implements ILogView {

    @BindView(R.id.group_chat)
    RecyclerView groupChat;
    Unbinder unbinder;

    private GroupChatAdapter adapter;
    private Presenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_group;
    }

    @Override
    protected void initListener() {
        groupChat.setLayoutManager(new LinearLayoutManager(getMContext()));
        presenter = new Presenter(this);
    }

    @Override
    protected void init() {
        presenter.information();
        adapter.spotButton(new Click() {
            @Override
            public void onClick(int position) {
                NewsActivity(presenter.util.datagroupChat().get(position).getTitle());
                MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
            }
        });

    }

    @Override
    public void show() {
        adapter = new GroupChatAdapter(getMContext(),presenter.util.datagroupChat());
        groupChat.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void NewsActivity(String name){
        Intent newsIntent = new Intent(getMContext(),NewsActivity.class);
        newsIntent.putExtra("GroupChatName",name);
        startActivity(newsIntent);
        MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
    }
}
