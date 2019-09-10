package com.xuanyin.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.xuanyin.payment.iu.activity.MainActivity;
import org.litepal.LitePal;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private static Activity mcontext;
    private static boolean isTrue;
    private static LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcontext = getContext();
        mcontext.setContentView(getContentId());
        isTrue = getStatusBar();
        ButterKnife.bind(mcontext);
        initview(savedInstanceState);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        LitePal.initialize(this);
    }

    /**
     * 初始化界面
     */
    private void initview(Bundle savedInstanceState){
        if (!isTrue){
            //判断Android版本
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                Window window = getContext().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
                View statusBarView = new View(window.getContext());
                int statusBarHeight = getStatusBarHeight(window.getContext());
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
                params.gravity = Gravity.TOP;
                statusBarView.setLayoutParams(params);
                statusBarView.setBackgroundColor(getResources().getColor(R.color.toolbar_background));
                decorViewGroup.addView(statusBarView);
            }else if (Build.VERSION.SDK_INT > 21){
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //获取样式中的属性值
                TypedValue typedValue = new TypedValue();
                this.getTheme().resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
                int[] attribute = new int[]{android.R.attr.colorPrimary};
                TypedArray array = this.obtainStyledAttributes(typedValue.resourceId, attribute);
                int color = array.getColor(0, Color.BLACK);
                array.recycle();
                window.setStatusBarColor(color);
            }
        }
        loadViewLayout();
        processLogic(savedInstanceState);
        setListener();
    }

    protected abstract Activity getContext();

    protected abstract int getContentId();

    protected abstract boolean getStatusBar();



    /**
     * 加载布局
     */
    protected abstract void loadViewLayout();

    /**
     * 处理数据
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 界面跳转
     *
     * @param tarActivity
     */
    protected void intentActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mcontext, tarActivity);
        startActivity(intent);
        MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
    }

    protected LinearLayoutManager getManager(){
        layoutManager = new LinearLayoutManager(this);
        return layoutManager;
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    protected int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
