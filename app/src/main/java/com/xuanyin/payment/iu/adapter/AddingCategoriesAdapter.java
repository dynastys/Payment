package com.xuanyin.payment.iu.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.AddingCategories;
import java.util.List;

public class AddingCategoriesAdapter extends BaseQuickAdapter<AddingCategories,BaseViewHolder> {

    private Context mContext;
    public AddingCategoriesAdapter(Context context, List<AddingCategories> data) {
        super(R.layout.adapyer_adding, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddingCategories item) {
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.image));
        helper.setText(R.id.text,item.getName());
    }
}
