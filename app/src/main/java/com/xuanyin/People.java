package com.xuanyin;

import android.graphics.Bitmap;
import org.litepal.crud.LitePalSupport;


public class People extends LitePalSupport {
    private String groupname;
    private String name;
    private int image;
    private String money;
    private boolean drawee;
    private boolean Participants;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isDrawee() {
        return drawee;
    }

    public void setDrawee(boolean drawee) {
        this.drawee = drawee;
    }

    public boolean isParticipants() {
        return Participants;
    }

    public void setParticipants(boolean participants) {
        Participants = participants;
    }
}
