package com.xuanyin.payment.iu.activity.huodong.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.Member;

import java.util.ArrayList;
import java.util.List;


public class Invitation_of_MembersAdapter extends BaseQuickAdapter<Member,BaseViewHolder> {

    private List<Boolean> isclick = new ArrayList<>();
    private Context mContext;
    public Invitation_of_MembersAdapter(Context context,List<Member> data) {
        super(R.layout.invitation_of_members, data);
        this.mContext = context;
        for (int i = 0; i < data.size(); i++) {
            isclick.add(false);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, Member item) {
        final int position = helper.getLayoutPosition();
        CheckBox checkBox = helper.getView(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isclick.set(position,isChecked);
                }
            }
        });
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.circle));
        helper.setText(R.id.name,item.getName());
        checkBox.setChecked(isclick.get(position));
    }
}
