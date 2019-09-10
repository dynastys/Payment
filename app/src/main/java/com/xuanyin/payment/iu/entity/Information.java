package com.xuanyin.payment.iu.entity;

import android.graphics.Bitmap;

public class Information {

    private String name;
    private Bitmap[] bitmap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap[] getImage() {
        return bitmap;
    }

    public void setImage(Bitmap[] bitmap) {
        this.bitmap = bitmap;
    }
}
