package com.xuanyin.shijianzhou;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;

import java.util.List;

public class NewsAdapter extends BaseQuickAdapter<TimeLineData.DataList,BaseViewHolder> {

    public NewsAdapter(List<TimeLineData.DataList> data) {
        super(R.layout.item_news_title, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeLineData.DataList item) {
        helper.setText(R.id.news_title,item.getTitle());
    }
}
