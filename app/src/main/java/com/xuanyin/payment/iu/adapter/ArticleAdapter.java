package com.xuanyin.payment.iu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xuanyin.payment.R;
import com.xuanyin.payment.group.GroupRecyclerAdapter;
import com.xuanyin.payment.iu.entity.Article;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class ArticleAdapter extends GroupRecyclerAdapter<String, Article> {

    private Context mContext;
    public ArticleAdapter(Context context, List<Article> articles) {
        super(context);
        this.mContext = context;
        LinkedHashMap<String, List<Article>> map = new LinkedHashMap<>();
        List<String> titles = new ArrayList<>();
        map.put("今日推荐", articles);
        resetGroups(map,titles);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ArticleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_article,parent,false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Article item, int position) {
        ArticleViewHolder h = (ArticleViewHolder) holder;
        h.mTextTitle.setText(item.getText());
    }

    private static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle;


        private ArticleViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text);
        }
    }

//    public ArticleAdapter(List<Article> data) {
//        super(R.layout.adapter_article, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, Article item) {
//        helper.setText(R.id.text,item.getText());
//    }
}
