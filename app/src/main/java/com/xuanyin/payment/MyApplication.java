package com.xuanyin.payment;

import android.app.Application;
import com.umeng.commonsdk.UMConfigure;

import org.litepal.LitePalApplication;

public class MyApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"");
    }
}
