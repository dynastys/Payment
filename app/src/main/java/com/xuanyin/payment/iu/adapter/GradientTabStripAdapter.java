package com.xuanyin.payment.iu.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.fragment.Fragment1;
import com.xuanyin.payment.iu.fragment.Fragment2;
import com.xuanyin.payment.iu.fragment.Fragment3;
import com.xuanyin.payment.iu.fragment.Fragment4;

import am.widget.gradienttabstrip.GradientTabStrip;

public class GradientTabStripAdapter extends FragmentPagerAdapter implements
        GradientTabStrip.GradientTabAdapter{


    public GradientTabStripAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return "账单";
            case 1:
                return "账本";
            case 2:
                return "报表";
            case 3:
                return "个人";

        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Drawable getNormalDrawable(int position, Context context) {
        switch (position) {
            default:
            case 0:
                return ContextCompat.getDrawable(context, R.drawable.bill_no);
            case 1:
                return ContextCompat.getDrawable(context, R.drawable.account_book_no);
            case 2:
                return ContextCompat.getDrawable(context,R.drawable.graphical_no);
            case 3:
                return ContextCompat.getDrawable(context,R.drawable.personal_no);

        }
    }

    @Override
    public Drawable getSelectedDrawable(int position, Context context) {
        switch (position) {
            default:
            case 0:
                return ContextCompat.getDrawable(context, R.drawable.bill);
            case 1:
                return ContextCompat.getDrawable(context, R.drawable.account_book);
            case 2:
                return ContextCompat.getDrawable(context, R.drawable.graphical);
            case 3:
                return ContextCompat.getDrawable(context,R.drawable.personal);
        }
    }

    @Override
    public boolean isTagEnable(int position) {
        return false;
    }

    @Override
    public String getTag(int position) {
        return null;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            default:
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();

        }
    }


}
