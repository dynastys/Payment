package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xuanyin.People;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.Group_chat.adapter.ConsumptionAdapter;
import com.xuanyin.payment.iu.entity.Group_detailed;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.utils.All;
import com.xuanyin.payment.view.ILogView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends BaseActivity implements ILogView {

    @BindView(R.id.news_details_recyc)
    RecyclerView newsDetailsRecyc;
    private ConsumptionAdapter adapter;
    private Presenter presenter;

    private List<Group_detailed> GD = new ArrayList<>();

    @Override
    protected Activity getContext() {
        return NewsDetailsActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_news_details;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {

        newsDetailsRecyc.setOverScrollMode(View.OVER_SCROLL_NEVER);
        newsDetailsRecyc.setLayoutManager(getManager());
        presenter = new Presenter(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        presenter.information();
        adapter = new ConsumptionAdapter(GD);
        newsDetailsRecyc.setAdapter(adapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void show() {
        List<People> people = presenter.util.dataConsumption();
        for (int i = 0; i < people.size(); i++) {
            if (All.GroupName.equals(people.get(i).getGroupname())) {
                Group_detailed gd = new Group_detailed();
                gd.setName(people.get(i).getName());
                gd.setBitmap(people.get(i).getImage());
                GD.add(gd);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
