package com.xuanyin.payment.iu.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SnAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String []titles;
    public SnAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public void addTitlesAndFragments(String []titles, List<Fragment> fragments) {
        this.titles = titles;
        this.fragments = fragments;
    }

}
