package com.xuanyin.payment.iu.Group_chat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.People;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Check;
import com.xuanyin.payment.intefaceGroup.Click;
import com.xuanyin.payment.iu.entity.Consumption;
import com.xuanyin.payment.iu.entity.Friends;
import com.xuanyin.payment.iu.entity.Group_detailed;

import java.util.ArrayList;
import java.util.List;

public class MovableAdapter extends BaseQuickAdapter <Group_detailed,BaseViewHolder>{

    private Click click;
    private Check check;
    public List<Boolean> isCheck = new ArrayList<>();
    private Context mContext;
    private int first;
    private String number;
    public MovableAdapter(Context context,List<Group_detailed> data, String number) {
        super(R.layout.adapter_movable, data);
        this.mContext = context;
        this.number = number;
        if (number.equals("1")){
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).isDrawee()){
                    first = i;
                }
                Log.i("TAGAAA",i+"");
                isCheck.add(data.get(i).isDrawee());
            }
        }else if (number.equals("2")){
            for (int i = 0; i < data.size(); i++) {
                Log.i("TAGAAA","2");
                isCheck.add(data.get(i).isParticipant());
            }
        }

    }

    @Override
    protected void convert(final BaseViewHolder helper, Group_detailed item) {
        helper.setText(R.id.name, item.getName());
        Glide.with(mContext).load(item.getBitmap()).into((ImageView) helper.getView(R.id.circle));
        final CheckBox checkBox = helper.getView(R.id.checkbox);

        final int position = helper.getLayoutPosition();
        if (number.equals("1")) {

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (first != position){
                        isCheck.set(position,true);
                        isCheck.set(first,false);
                        first = position;
                        check.onCheck(isCheck);
                    }
                    notifyDataSetChanged();
                }
            });
        } else if (number.equals("2")) {
           checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   isCheck.set(position,isChecked);
                   check.onCheck(isCheck);
                   //notifyDataSetChanged();
               }
           });
        }
        checkBox.setChecked(isCheck.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setClick(Click click){
        this.click = click;
    }

    public void setCheck(Check check){
        this.check = check;
    }

}
