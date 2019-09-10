package com.xuanyin.payment.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.xuanyin.payment.iu.entity.AddressBook;
import com.xuanyin.payment.iu.entity.PhoneDto;

import java.util.ArrayList;
import java.util.List;

public class PhoneUtil {

    private static PhoneUtil phoneUtil;

    // 号码
    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

    //上下文对象
    private static Context context;
    //联系人提供者的uri
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public static PhoneUtil getPhoneUtil(Context mContext){
        context = mContext;
        if (phoneUtil == null){
            synchronized (PhoneUtil.class){
                if (phoneUtil == null){
                    phoneUtil = new PhoneUtil();
                }
            }
        }
        return phoneUtil;
    }

    //获取所有联系人
    public List<AddressBook> getPhone(){
        List<AddressBook> addressBooks = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(phoneUri,new String[]{NUM,NAME},null,null,null);
        while (cursor.moveToNext()){
            AddressBook addressBook = new AddressBook();
            addressBook.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            addressBook.setPhone(cursor.getString(cursor.getColumnIndex(NUM)));
            addressBooks.add(addressBook);
        }
        return addressBooks;
    }

}
