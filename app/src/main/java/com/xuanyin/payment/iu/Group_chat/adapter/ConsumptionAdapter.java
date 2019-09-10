package com.xuanyin.payment.iu.Group_chat.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.People;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.Consumption;
import com.xuanyin.payment.iu.entity.Friends;
import com.xuanyin.payment.iu.entity.Group_detailed;
import com.xuanyin.payment.utils.Utils;

import java.util.List;

public class ConsumptionAdapter extends BaseQuickAdapter<Group_detailed, BaseViewHolder> {


    public ConsumptionAdapter(List<Group_detailed> data) {
        super(R.layout.adapter_consumption, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Group_detailed item) {
        helper.setText(R.id.consumption_name, item.getName());
        helper.setText(R.id.consumption_money, item.getMoney());
    }


}
