package com.xuanyin;

import android.graphics.Bitmap;

import org.litepal.crud.LitePalSupport;

public class Classificaty extends LitePalSupport {

    private int image;
    private String name;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
