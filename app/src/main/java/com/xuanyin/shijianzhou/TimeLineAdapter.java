package com.xuanyin.shijianzhou;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;

import java.util.List;

public class TimeLineAdapter extends BaseQuickAdapter<TimeLineData,BaseViewHolder> {

    private Context mContext;
    public TimeLineAdapter(Context context,List<TimeLineData> data) {
        super(R.layout.adapter_timeline, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeLineData item) {
        helper.setText(R.id.time,item.getDataString());
        helper.setText(R.id.news_number,item.getDataList().size()+"");
        RecyclerView recyclerView = helper.getView(R.id.news_list);
        LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        NewsAdapter adapter = new NewsAdapter(item.getDataList());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
