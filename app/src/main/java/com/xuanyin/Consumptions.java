package com.xuanyin;

import android.graphics.drawable.Drawable;
import org.litepal.crud.LitePalSupport;

public class Consumptions extends LitePalSupport {
    private int type;
    private String group_name;
    private String data;
    private String type_name;
    private Drawable type_img;
    private String money;
    private String drawee;
    private String remarks;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Drawable getType_img() {
        return type_img;
    }

    public void setType_img(Drawable type_img) {
        this.type_img = type_img;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDrawee() {
        return drawee;
    }

    public void setDrawee(String drawee) {
        this.drawee = drawee;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
