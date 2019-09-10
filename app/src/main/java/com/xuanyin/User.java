package com.xuanyin;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private String name;
    private String phone;
    private int bitmap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBitmap() {
        return bitmap;
    }

    public void setBitmap(int bitmap) {
        this.bitmap = bitmap;
    }
}
