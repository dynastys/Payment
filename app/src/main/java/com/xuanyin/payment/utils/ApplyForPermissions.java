package com.xuanyin.payment.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ApplyForPermissions {

    private static ApplyForPermissions applyForPermissions;
    private static final int mRequestCode = 100;//权限请求码
    private static IPermissionsResult mPermissionsResult;
    private static Context context;
    public static ApplyForPermissions getInstence(Context mcontext){
        context = mcontext;
        if (applyForPermissions == null){
            synchronized (ApplyForPermissions.class){
                if (applyForPermissions == null){
                    applyForPermissions = new ApplyForPermissions();
                }
            }
        }
        return applyForPermissions;
    }

    private static void chekPermissions(String[] permissions,IPermissionsResult permissionsResult) {
        mPermissionsResult = permissionsResult;
        //创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
        List<String> mPermissionList = new ArrayList<>();
        //逐个判断你要的权限是否已经通过
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions((Activity) context, permissions, mRequestCode);
        } else {
            //说明权限都已经通过，可以做你想做的事情去
            mPermissionsResult.passPermissons();
            return;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                mPermissionsResult.forbitPermissons();
            } else {
                //全部权限通过，可以进行下一步操作。。。
                mPermissionsResult.passPermissons();
            }
        }

    }



    public static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"
    };

    public static void ApplyFor(IPermissionsResult result){
        if (Build.VERSION.SDK_INT >= 23) {//判断当前系统是不是Android6.0
            chekPermissions(PERMISSIONS_STORAGE,result);
        }
    }

    public interface IPermissionsResult {
        void passPermissons();

        void forbitPermissons();
    }

}
