package com.xuanyin.payment.iu.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.Group_chat.activity.NoteActivity;
import com.xuanyin.payment.iu.activity.AddressBookActivity;
import com.xuanyin.payment.iu.entity.MultipleItem;

import java.util.List;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem,BaseViewHolder> {
    private Context mContext;
    public MultipleItemQuickAdapter(Context context,List<MultipleItem> data) {
        super(data);
        this.mContext = context;
        addItemType(MultipleItem.NEWS, R.layout.adapter_news);
        addItemType(MultipleItem.ACTIVITY, R.layout.adapter_activity);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {


            case MultipleItem.NEWS:
                Glide.with(mContext).load(item.getNews_img_team_type()).into((ImageView) helper.getView(R.id.img_team_type));
                helper.setText(R.id.text_team_type,item.getNews_text_team_type());
                helper.setText(R.id.text_team_time,item.getNews_text_team_time());
                helper.setText(R.id.text_team_money,item.getNews_text_team_money());
                helper.setText(R.id.text_team_people,item.getNews_text_team_people());

                helper.getView(R.id.rela).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent news = new Intent(mContext,NoteActivity.class);
                        mContext.startActivity(news);
                    }
                });

                break;
            case MultipleItem.ACTIVITY:

                helper.setText(R.id.title,item.getMultiples().get(getLoadMoreViewPosition()).getActivity_title());
//                helper.setText(R.id.number,item.getMultiples());
                helper.getView(R.id.rela).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent act = new Intent(mContext,AddressBookActivity.class);
                        mContext.startActivity(act);
                    }
                });
                break;
        }
    }

}
