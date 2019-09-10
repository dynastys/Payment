package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xuanyin.Classificaty;
import com.xuanyin.Demo;
import com.xuanyin.People;
import com.xuanyin.User;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.GradientTabStripAdapter;
import com.xuanyin.payment.iu.entity.AddingCategories;
import com.xuanyin.payment.utils.ApplyForPermissions;
import com.xuanyin.payment.utils.Utils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import am.widget.basetabstrip.BaseTabStrip;
import am.widget.gradienttabstrip.GradientTabStrip;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, BaseTabStrip.OnItemClickListener,ApplyForPermissions.IPermissionsResult {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_strip)
    GradientTabStrip tabStrip;
    private GradientTabStripAdapter tabStripAdapter;
    public static MainActivity mActivity;
    SharedPreferences save;

    @Override
    protected Activity getContext() {
        return MainActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        mActivity = this;
        tabStripAdapter = new GradientTabStripAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabStripAdapter);
        tabStrip.setAdapter(tabStripAdapter);
        viewPager.addOnPageChangeListener(this);
        tabStrip.bindViewPager(viewPager);
        tabStrip.setOnItemClickListener(this);
        save = getSharedPreferences("SAVE",MODE_PRIVATE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void processLogic(Bundle savedInstanceState) {
        // 隐藏pad底部虚拟键
//        Window _window = getWindow();
//        WindowManager.LayoutParams params = _window.getAttributes();
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
//        _window.setAttributes(params);

        ApplyForPermissions.getInstence(getContext()).ApplyFor(this);

//        LitePal.deleteAll(Classificaty.class);
//        Log.i("TAGAAA",save.getString("isfirst","false"));
        if (save.getString("isfirst","false").equals("false")){
            save();
        }







    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        ApplyForPermissions.getInstence(this).onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onSelectedClick(int position) {

    }

    @Override
    public void onDoubleClick(int position) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void passPermissons() {

    }

    @Override
    public void forbitPermissons() {

    }

    private void save(){
        Integer [] img = {R.mipmap.icon_shouru_type_qita,R.mipmap.icon_zhichu_type_canyin,R.mipmap.icon_jiaotongchuxing
                ,R.mipmap.jiushui ,R.mipmap.icon_zhichu_type_shuiguolingshi,R.mipmap.icon_lingshixiaochi,R.mipmap.maicai
                ,R.mipmap.yifu,R.mipmap.richangyongpin,R.mipmap.icon_zhichu_type_shoujitongxun,R.mipmap.huazhuangpin
                ,R.mipmap.fangzu,R.mipmap.dianying,R.mipmap.icon_zhichu_type_taobao,R.mipmap.hongbaolicai
                ,R.mipmap.yaopinfei
        };
        String [] name = {"一般","餐饮","交通","酒水饮料","水果","零食","买菜","衣服鞋包","生活用品","花费","护肤彩妆","房租","电影","淘宝","红包","药品"};
        for (int i = 0; i < img.length; i++) {
            Classificaty classificaty = new Classificaty();
            classificaty.setImage(img[i]);
            classificaty.setName(name[i]);
            classificaty.save();
        }

        SharedPreferences.Editor e = save.edit();
        e.putString("isfirst","true");
        e.commit();
    }
}
