package com.xuanyin.payment.iu.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xuanyin.payment.BaseFragment;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.SnAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment2 extends BaseFragment{

    Unbinder unbinder;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.tl_main)
    TabLayout tlMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    List<Fragment> mFragments = new ArrayList<>();

    private SnAdapter adapter;
    private String[] mTitles = {"好友","群聊"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_2;
    }

    @Override
    protected void initListener() {

        for (int i = 0; i < mTitles.length; i++) {
            tlMain.addTab(tlMain.newTab().setText(mTitles[i]));
        }

        FriendFragment friendFragment = new FriendFragment();
        GroupFragment groupFragment = new GroupFragment();

        mFragments.add(friendFragment);
        mFragments.add(groupFragment);
        adapter = new SnAdapter(getChildFragmentManager());
        adapter.addTitlesAndFragments(mTitles, mFragments);
        vpMain.setAdapter(adapter);
        tlMain.setupWithViewPager(vpMain);

    }

    @Override
    protected void init() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
