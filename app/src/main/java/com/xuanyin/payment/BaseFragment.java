package com.xuanyin.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.xuanyin.payment.iu.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private View mContentView;
    private Context mContext;
    private static LinearLayoutManager layoutManager;
    private Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(),container,false);
        // 初始化View注入
        ButterKnife.bind(this,mContentView);
        mContext = getMContext();
        initListener();
        init();
        return mContentView;
    }

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    public Context getMContext() {
        return getActivity();
    }

    /**
     * 加载布局
     */
    protected abstract void initListener();

    /**
     * 处理数据
     */
    protected abstract void init();


    /**
     * 界面跳转
     *
     * @param tarActivity
     */
    protected void intentActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mContext, tarActivity);
        startActivity(intent);
        MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
    }

    protected LinearLayoutManager getManager(){
        layoutManager = new LinearLayoutManager(getMContext());

//        layoutManager = new LinearLayoutManager(getMContext()){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
        return layoutManager;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
