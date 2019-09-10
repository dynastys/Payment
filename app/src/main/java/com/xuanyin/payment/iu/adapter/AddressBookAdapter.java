package com.xuanyin.payment.iu.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.User;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.AddressBook;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AddressBookAdapter extends BaseQuickAdapter<AddressBook,BaseViewHolder> {

    private List<Boolean> isfalse = new ArrayList<>();
    private List<User> userList;

    public AddressBookAdapter(List<AddressBook> data) {
        super(R.layout.adapter_addressbook, data);
        userList = LitePal.findAll(User.class);
        for (int i = 0; i < data.size(); i++) {
            isfalse.add(i,true);
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AddressBook item) {

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equals(item.getName()) && userList.get(i).getPhone().equals(item.getPhone())){
                isfalse.set(helper.getLayoutPosition(),false);
            }
        }

        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.phone,item.getPhone());

        helper.getView(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isfalse.get(helper.getLayoutPosition())){
                    User user = new User();
                    user.setName(item.getName());
                    user.setPhone(item.getPhone());
                    user.setBitmap(R.mipmap.icon);
                    user.save();
                }
                isfalse.set(helper.getLayoutPosition(),false);
                notifyDataSetChanged();
            }
        });
        if (!isfalse.get(helper.getLayoutPosition())){
            helper.setText(R.id.add,"已添加");
        }
    }
}
