package com.xuanyin.payment.iu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.xuanyin.payment.BaseFragment;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Click;
import com.xuanyin.payment.iu.activity.AddGroupActivity;
import com.xuanyin.payment.iu.activity.CalendarActivity;
import com.xuanyin.payment.iu.activity.MainActivity;
import com.xuanyin.payment.iu.Group_chat.activity.NewsActivity;
import com.xuanyin.payment.iu.activity.ReportFormActivity;
import com.xuanyin.payment.iu.adapter.GroupChatAdapter;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.view.ILogView;
import com.xuanyin.shijianzhou.TimeLineAdapter;
import com.xuanyin.shijianzhou.TimeLineData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment3 extends BaseFragment implements ILogView {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.group_chat)
    RecyclerView groupChat;
    Unbinder unbinder;

    //private GroupChatAdapter adapter;
    private Presenter presenter;
    TimeLineAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_3;
    }

    @Override
    protected void initListener() {
        groupChat.setLayoutManager(new LinearLayoutManager(getMContext()));
        presenter = new Presenter(this);
    }

    @Override
    protected void init() {
        initData();
        groupChat.setAdapter(adapter);


//        presenter.information();
//        adapter.spotButton(new Click() {
//            @Override
//            public void onClick(int position) {
//                NewsActivity(presenter.util.datagroupChat().get(position).getTitle());
//                MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
//            }
//        });

    }

    @Override
    public void show() {
//        adapter = new GroupChatAdapter(getMContext(),presenter.util.datagroupChat());
//        groupChat.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void addActivity() {
        intentActivity(CalendarActivity.class);
        MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    private void reportformActivity() {
        intentActivity(ReportFormActivity.class);
        MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    private void NewsActivity(String name){
        Intent newsIntent = new Intent(getMContext(),NewsActivity.class);
        newsIntent.putExtra("GroupChatName",name);
        startActivity(newsIntent);
        MainActivity.mActivity.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_from_left);
    }

    @OnClick({R.id.img_add, R.id.img_report_form})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_add:
                addActivity();
                break;
            case R.id.img_report_form:
                reportformActivity();
                break;
        }
    }

    private void initData(){
        List<TimeLineData> timeLineData=new ArrayList<>();

        TimeLineData timeLineData1=new TimeLineData();
        timeLineData1.setDataString("2019-04-24");
        List<TimeLineData.DataList> dataLists1=new ArrayList<>();
        TimeLineData.DataList data1=new TimeLineData.DataList();
        data1.setId("111111111111111");
        data1.setTitle("资讯标题1");
        data1.setReleaseDate("2019-04-24");
        TimeLineData.DataList data2=new TimeLineData.DataList();
        data2.setId("22222222222");
        data2.setTitle("资讯标题2");
        data2.setReleaseDate("2019-04-24");
        dataLists1.add(data1);
        dataLists1.add(data2);
        timeLineData1.setDataList(dataLists1);
        timeLineData.add(timeLineData1);

        TimeLineData timeLineData2=new TimeLineData();
        timeLineData2.setDataString("2019-04-25");
        List<TimeLineData.DataList> dataLists2=new ArrayList<>();
        TimeLineData.DataList data3=new TimeLineData.DataList();
        data3.setId("33333333");
        data3.setTitle("资讯标题3");
        data3.setReleaseDate("2019-04-24");
        TimeLineData.DataList data4=new TimeLineData.DataList();
        data4.setId("444444444");
        data4.setTitle("资讯标题4");
        data4.setReleaseDate("2019-04-24");
        TimeLineData.DataList data5=new TimeLineData.DataList();
        data5.setId("55555555");
        data5.setTitle("资讯标题5");
        data5.setReleaseDate("2019-04-24");
        dataLists2.add(data3);
        dataLists2.add(data4);
        dataLists2.add(data5);
        timeLineData2.setDataList(dataLists2);
        timeLineData.add(timeLineData2);

        TimeLineData timeLineData3=new TimeLineData();
        timeLineData3.setDataString("2019-04-26");
        List<TimeLineData.DataList> dataLists3=new ArrayList<>();
        TimeLineData.DataList data6=new TimeLineData.DataList();
        data6.setId("33333333");
        data6.setTitle("资讯标题6");
        data6.setReleaseDate("2019-04-24");
        TimeLineData.DataList data7=new TimeLineData.DataList();
        data7.setId("444444444");
        data7.setTitle("资讯标题7");
        data7.setReleaseDate("2019-04-24");
        TimeLineData.DataList data8=new TimeLineData.DataList();
        data8.setId("55555555");
        data8.setTitle("资讯标题8");
        data8.setReleaseDate("2019-04-24");
        dataLists3.add(data6);
        dataLists3.add(data7);
        dataLists3.add(data8);
        timeLineData3.setDataList(dataLists3);
        timeLineData.add(timeLineData3);


        adapter = new TimeLineAdapter(getContext(),timeLineData);
        adapter.notifyDataSetChanged();

    }


}
