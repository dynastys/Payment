package com.xuanyin.payment.iu.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.Friends;
import java.util.List;

public class FriendAdapter extends BaseQuickAdapter<Friends,BaseViewHolder> {

    private Context mContext;
    public FriendAdapter(Context context,List<Friends> data) {
        super(R.layout.adapter_friend, data);
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Friends item) {
        Glide.with(mContext).load(item.getBitmap()).into((ImageView) helper.getView(R.id.friend_img));
        helper.setText(R.id.friend_name,item.getName());
    }

}
