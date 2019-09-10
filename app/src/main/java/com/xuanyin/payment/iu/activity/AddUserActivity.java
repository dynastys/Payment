package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xuanyin.User;
import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserActivity extends BaseActivity {

    @BindView(R.id.edit_user)
    EditText editUser;
    @BindView(R.id.edit_photo)
    EditText editPhoto;

    private boolean existence = true;
    List<User> users;
    @Override
    protected Activity getContext() {
        return AddUserActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_adduser;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        users = LitePal.findAll(User.class);


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.quxiao, R.id.queding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quxiao:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.queding:
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getName().equals(editUser.getText().toString())){
                        existence = false;
                    }
                }
                if (!TextUtils.isEmpty(editUser.getText()) && existence) {
                    User user = new User();
                    user.setName(editUser.getText().toString());
                    user.setPhone(editPhoto.getText().toString());
                    user.setBitmap(R.mipmap.nice);
                    user.save();
                }

                AppManager.getAppManager().finishActivity();
                break;
        }
    }
}
