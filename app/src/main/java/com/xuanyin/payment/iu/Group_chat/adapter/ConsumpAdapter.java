package com.xuanyin.payment.iu.Group_chat.adapter;

import android.widget.EditText;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.Group_detailed;
import com.xuanyin.payment.utils.Utils;
import java.util.List;

public class ConsumpAdapter extends BaseQuickAdapter<Group_detailed,BaseViewHolder> {

    public ConsumpAdapter(List<Group_detailed> data) {
        super(R.layout.adapter_consump, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Group_detailed item) {
        helper.addOnClickListener(R.id.consumption_money);
        final EditText editText = helper.getView(R.id.consumption_money);
        Utils.setPricePoint(editText);
        helper.setText(R.id.consumption_name,item.getName());
        helper.setText(R.id.consumption_money,item.getMoney());
    }






}
