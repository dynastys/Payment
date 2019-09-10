package com.xuanyin.payment.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Utils {

    /**
     * 限制edittext只能输入小数点后两位数,小数点前8位
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        Log.i("TAGAAA",s+"");
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                } else {
                    if (s.length() > 8) {
                        editText.setText(s.subSequence(0, 8));
                        editText.setSelection(s.length() - 1);
                    }
                }

                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });
    }

    public static String doubleTrans(double num){
        if(Math.round(num)-num==0){
            return String.valueOf((long)num);
        }
        return String.valueOf(num);
    }

    public static Bitmap Rfile2Bitmap(Context context,int image){
        //修改R.drawable.pic为你自己的文件即可
        return BitmapFactory.decodeResource(context.getResources(), image);//此处实现文件类型的转换
    }

    /**
     * String转换成double 保留N位小数。
     * @param number
     * @return
     */
    public static double stringToDouble(String number){
        if (TextUtils.isEmpty(number)) {
            return 0.00;
        }
        double b = Double.valueOf(number);
        DecimalFormat df = new DecimalFormat("#.00");//此为保留1位小数，若想保留2位小数，则填写#.00  ，以此类推
        String temp = df.format(b);
        b = Double.valueOf(temp);
        return b;
    }

    /**
     * double转String,保留小数点后两位
     * @param number
     * @return
     */
    public static String doubleToString(double number){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(number);
    }


    //把String转化为double
    private static double convertToDouble(String number) {
        if (TextUtils.isEmpty(number)) {
            return 0.00;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return 0.00;
        }

    }

    /**
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     * @param
     * @return
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if(obj.compareTo(BigDecimal.ZERO)==0) {
            return "0.00";
        }else if(obj.compareTo(BigDecimal.ZERO)>0&&obj.compareTo(new BigDecimal(1))<0){
            return "0"+df.format(obj).toString();
        }else {
            return df.format(obj).toString();
        }
    }

}
