package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.utils.CountDownView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class StartActivity extends BaseActivity {

    @BindView(R.id.skipBtn)
    CountDownView skipBtn;
    private Timer mTimer = new Timer();

//    private CountdownCircleProgressBar mCircleProgressBar;
    /**
     * 开屏时长固定为5000ms
     */
    public final static int OPEN_SCREEN_TIME = 5000;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Activity getContext() {
        return StartActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_start;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        skipBtn.setTimeMillis(OPEN_SCREEN_TIME);
        skipBtn.play();
        //使用计时器Task来等待5秒跳转
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startMainActivity();
            }
        };
        mTimer.schedule(timerTask,OPEN_SCREEN_TIME);

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        skipBtn.isRunning = false;
        mTimer.cancel();
    }

    private void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        //右边进左边出
        //overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
        //左边进右边出
        overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
    }

}
