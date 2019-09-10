package com.xuanyin.payment.iu.Group_chat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xuanyin.People;
import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Check;
import com.xuanyin.payment.iu.Group_chat.adapter.MovableAdapter;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.view.ILogView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMovableAdtivity extends BaseActivity implements ILogView {

    @BindView(R.id.rele_friend)
    RecyclerView releFriend;
    @BindView(R.id.tool_text)
    TextView toolText;
    MovableAdapter adapter;
    Presenter presenter;
    private String num;
    private String name;
    private List<People> people;
    private List<Integer> integers;

    @Override
    protected Activity getContext() {
        return AddMovableAdtivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_addmovable;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        integers = new ArrayList<>();
        releFriend.setLayoutManager(new LinearLayoutManager(this));
        presenter = new Presenter(this);
        presenter.information();
        num = getIntent().getStringExtra("NUM");
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

        if (num.equals("1")){
            adapter = new MovableAdapter(getContext(),NoteActivity.GD,num);
            adapter.setCheck(new Check() {
                @Override
                public void onCheck(List<Boolean> list) {
                    for (int i = 0; i < list.size(); i++) {
                       NoteActivity.GD.get(i).setDrawee(list.get(i));
                    }
                }
            });
        }else if (num.equals("2")){
            adapter = new MovableAdapter(getContext(),NoteActivity.GD,num);
            adapter.setCheck(new Check() {
                @Override
                public void onCheck(List<Boolean> list) {
                    for (int i = 0; i < list.size(); i++) {
                        NoteActivity.GD.get(i).setParticipant(list.get(i));
                    }
                }
            });
        }

        releFriend.setAdapter(adapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void show()  {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.text_cancel, R.id.text_establish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_cancel:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.text_establish:
                if (num.equals("1")){
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    AppManager.getAppManager().finishActivity();
                }else if (num.equals("2")){
                    for (int i = 0; i < adapter.isCheck.size(); i++) {
                        if (adapter.isCheck.get(i)){
                            integers.add(i);
                        }
                    }
                    if (integers.size() != 0){
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        AppManager.getAppManager().finishActivity();
                    }
                }
                break;
        }
    }
}
