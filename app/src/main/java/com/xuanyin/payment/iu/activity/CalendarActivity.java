package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.group.GroupItemDecoration;
import com.xuanyin.payment.group.GroupRecyclerView;
import com.xuanyin.payment.iu.adapter.ArticleAdapter;
import com.xuanyin.payment.iu.entity.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener {


    @BindView(R.id.ib_calendar)
    ImageView ibCalendar;
    @BindView(R.id.tv_current_day)
    TextView tvCurrentDay;
    @BindView(R.id.fl_current)
    FrameLayout flCurrent;
    @BindView(R.id.rl_tool)
    RelativeLayout rlTool;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.recyclerView)
    GroupRecyclerView recyclerView;
    @BindView(R.id.calendarLayout)
    CalendarLayout calendarLayout;
    @BindView(R.id.tv_month_day)
    TextView tvMonthDay;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lunar)
    TextView tvLunar;

    List<Article> articles;

    @Override
    protected Activity getContext() {
        return CalendarActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_calendar;
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
        initView();
    }

    @Override
    protected void setListener() {

    }

    private void initView() {
        flCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.scrollToCurrent();
            }
        });
        calendarLayout = findViewById(R.id.calendarLayout);
        calendarView.setOnCalendarSelectListener(this);
        tvYear.setText(String.valueOf(calendarView.getCurYear()));

        tvMonthDay.setText(calendarView.getCurMonth() + "月" + calendarView.getCurDay() + "日");
        calendarView.setOnYearChangeListener(this);
        tvCurrentDay.setText(String.valueOf(calendarView.getCurDay()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.setText("李知恩" +i);
            articles.add(article);
        }
        recyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        recyclerView.setAdapter(new ArticleAdapter(getContext(),articles));
        recyclerView.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onYearChange(int year) {
        tvMonthDay.setText(String.valueOf(year));
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        tvLunar.setVisibility(View.VISIBLE);
        tvYear.setVisibility(View.VISIBLE);
        tvMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        tvYear.setText(String.valueOf(calendar.getYear()));
        tvLunar.setText(calendar.getLunar());
        //mYear = calendar.getYear();
        articles.clear();
        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.setText("李知恩SADW" +i);
            articles.add(article);
        }
        recyclerView.setAdapter(new ArticleAdapter(getContext(),articles));
        recyclerView.notifyDataSetChanged();
    }
}
