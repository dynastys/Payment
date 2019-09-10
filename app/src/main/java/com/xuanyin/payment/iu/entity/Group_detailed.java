package com.xuanyin.payment.iu.entity;

public class Group_detailed {
    private String name;
    private String money;
    private int bitmap;
    private String phone;
    private boolean drawee;
    private boolean participant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getBitmap() {
        return bitmap;
    }

    public void setBitmap(int bitmap) {
        this.bitmap = bitmap;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDrawee() {
        return drawee;
    }

    public void setDrawee(boolean drawee) {
        this.drawee = drawee;
    }

    public boolean isParticipant() {
        return participant;
    }

    public void setParticipant(boolean participant) {
        this.participant = participant;
    }
}
