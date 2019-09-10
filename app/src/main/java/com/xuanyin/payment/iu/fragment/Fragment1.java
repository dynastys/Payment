package com.xuanyin.payment.iu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xuanyin.ConsumptionDetails;
import com.xuanyin.Consumptions;
import com.xuanyin.payment.BaseFragment;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Click;
import com.xuanyin.payment.iu.activity.AddGroupActivity;
import com.xuanyin.payment.iu.activity.IncreaseActivity;
import com.xuanyin.payment.iu.activity.MainActivity;
import com.xuanyin.payment.iu.Group_chat.activity.NewsActivity;
import com.xuanyin.payment.iu.adapter.GroupChatAdapter;
import com.xuanyin.payment.iu.entity.Consumption;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.utils.CustomPopupWindow;
import com.xuanyin.payment.view.ILogView;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment1 extends BaseFragment implements ILogView {

    @BindView(R.id.news)
    RecyclerView news;
    Unbinder unbinder;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.img_add_peopel)
    ImageView imgAddPeopel;


    //private InformationAdapter adapter;
    private Presenter presenter;
    private CustomPopupWindow mCustomPopupWindow;
    private View mLayoutPopupWindowView;//悬浮窗的布局
    private GroupChatAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initListener() {
        news.setLayoutManager(getManager());
        news.setOverScrollMode(View.OVER_SCROLL_NEVER);
        presenter = new Presenter(this);
//        LitePal.deleteAll(User.class);
//        LitePal.deleteAll(People.class);
//        LitePal.deleteAll(Demo.class);
//        LitePal.deleteAll(Consumptions.class);
//        LitePal.deleteAll(ConsumptionDetails.class);


    }

    @Override
    protected void init() {
        presenter.information();
        adapter.spotButton(new Click() {
            @Override
            public void onClick(int position) {
                NewsActivity(presenter.util.datagroupChat().get(position).getTitle());
                MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
            }
        });
    }

    private void NewsActivity(String name) {
        Intent newsIntent = new Intent(getMContext(), NewsActivity.class);
        newsIntent.putExtra("GroupChatName", name);
        startActivity(newsIntent);
        MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    /**
     * 显示悬浮窗，并设置背景透明度
     */
    public void showPopupWindow() {
        mLayoutPopupWindowView = LayoutInflater.from(getMContext()).inflate(R.layout
                .popupwindow_activity_rule, null);
        mCustomPopupWindow = new CustomPopupWindow(imgAddPeopel,
                getActivity(), mLayoutPopupWindowView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mCustomPopupWindow.setOnPopupWindowListener(new CustomPopupWindow
                .PopupWindowListener() {
            @Override
            public void initView() {
                LinearLayout user = mLayoutPopupWindowView.findViewById(R.id.add_user);
                LinearLayout group = mLayoutPopupWindowView.findViewById(R.id.add_group);
                user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentActivity(IncreaseActivity.class);
                        mCustomPopupWindow.dismiss();
                    }
                });
                group.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentActivity(AddGroupActivity.class);
                        mCustomPopupWindow.dismiss();
                    }
                });
            }
        });
        mCustomPopupWindow.showView();
        mCustomPopupWindow.setBackgroundAlpha(0.8f);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void show() {
        adapter = new GroupChatAdapter(getMContext(),presenter.util.datagroupChat());
        news.setAdapter(adapter);
    }

    @OnClick(R.id.img_add_peopel)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_add_peopel:
                showPopupWindow();
                break;
        }
    }



}
