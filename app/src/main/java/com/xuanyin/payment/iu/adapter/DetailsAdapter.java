package com.xuanyin.payment.iu.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.GroupChat;
import java.util.List;

public class DetailsAdapter extends BaseQuickAdapter<GroupChat.PeopleGC,BaseViewHolder> {
    private Context mContext;

    private boolean istrue;
    public DetailsAdapter(Context context,List<GroupChat.PeopleGC> data,boolean istrue) {
        super(R.layout.adapter_details, data);
        this.mContext = context;
        this.istrue = istrue;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GroupChat.PeopleGC item) {
        if (istrue){
            helper.getView(R.id.member).setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.profile_image_member));
            helper.setText(R.id.text_details_member,item.getPeopel());
        }else {
            helper.getView(R.id.group).setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.profile_image_group));
            helper.setText(R.id.text_details_group,item.getPeopel());
        }

    }
}
