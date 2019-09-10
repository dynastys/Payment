package com.xuanyin.payment.iu.Group_chat.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Check;
import com.xuanyin.payment.iu.entity.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends BaseQuickAdapter <Member,BaseViewHolder>{

    public List<Boolean> ischeck = new ArrayList<>();
    private Context mContext;
    private Check check;
    public MemberAdapter(Context context, List<Member> data) {
        super(R.layout.adapter_movable, data);
        for (int i = 0; i < data.size(); i++) {
            ischeck.add(false);
        }
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, Member item) {
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.circle));
        helper.setText(R.id.name,item.getName());
        CheckBox checkBox = helper.getView(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ischeck.set(helper.getLayoutPosition(),isChecked);
                //check.onCheck(ischeck);
            }
        });
    }

    public void setCheck(Check check){
        this.check = check;
    }
}
