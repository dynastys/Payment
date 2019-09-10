package com.xuanyin.payment.iu.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuanyin.User;
import com.xuanyin.payment.BaseFragment;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.FriendAdapter;
import com.xuanyin.payment.iu.entity.Friends;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FriendFragment extends BaseFragment {
    @BindView(R.id.friend_recyc)
    RecyclerView friendRecyc;
    Unbinder unbinder;

    private FriendAdapter adapter;
    private List<Friends> friendsList;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initListener() {
        friendRecyc.setLayoutManager(new LinearLayoutManager(getMContext()));
        List<User> users = LitePal.findAll(User.class);

        friendsList = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            Friends friends = new Friends();
            friends.setName(users.get(i).getName());
            friends.setPhone(users.get(i).getPhone());
            friends.setBitmap(users.get(i).getBitmap());
            friendsList.add(friends);
        }
    }

    @Override
    protected void init(){
        adapter = new FriendAdapter(getMContext(),friendsList);
        friendRecyc.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
