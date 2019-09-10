package com.xuanyin.payment.iu.entity;

import android.graphics.drawable.Drawable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MultipleItem implements MultiItemEntity {

    public static final int NEWS = 1;
    public static final int ACTIVITY = 2;
    public int type;

    private String news_group_name;
    private String news_team_data;
    private Drawable news_img_team_type;
    private String news_text_team_type;
    private String news_text_team_time;
    private String news_text_team_money;
    private String news_text_team_people;
    private String news_team_remarks;
    private List<Multiple> multiples;

    public String getNews_group_name() {
        return news_group_name;
    }

    public void setNews_group_name(String news_group_name) {
        this.news_group_name = news_group_name;
    }

    public String getNews_team_data() {
        return news_team_data;
    }

    public void setNews_team_data(String news_team_data) {
        this.news_team_data = news_team_data;
    }

    public Drawable getNews_img_team_type() {
        return news_img_team_type;
    }

    public void setNews_img_team_type(Drawable news_img_team_type) {
        this.news_img_team_type = news_img_team_type;
    }

    public String getNews_text_team_type() {
        return news_text_team_type;
    }

    public void setNews_text_team_type(String news_text_team_type) {
        this.news_text_team_type = news_text_team_type;
    }

    public String getNews_text_team_time() {
        return news_text_team_time;
    }

    public void setNews_text_team_time(String news_text_team_time) {
        this.news_text_team_time = news_text_team_time;
    }

    public String getNews_text_team_money() {
        return news_text_team_money;
    }

    public void setNews_text_team_money(String news_text_team_money) {
        this.news_text_team_money = news_text_team_money;
    }

    public String getNews_text_team_people() {
        return news_text_team_people;
    }

    public void setNews_text_team_people(String news_text_team_people) {
        this.news_text_team_people = news_text_team_people;
    }

    public String getNews_team_remarks() {
        return news_team_remarks;
    }

    public void setNews_team_remarks(String news_team_remarks) {
        this.news_team_remarks = news_team_remarks;
    }

    public List<Multiple> getMultiples() {
        return multiples;
    }

    public void setMultiples(List<Multiple> multiples) {
        this.multiples = multiples;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

public static class Multiple{
    private String activity_title;
    private String activity_data;

    public Multiple(String activity_title, String activity_data) {
        this.activity_title = activity_title;
        this.activity_data = activity_data;
    }

    public String getActivity_title() {
        return activity_title;
    }

    public String getActivity_data() {
        return activity_data;
    }
}

    @Override
    public int getItemType() {
        return type;
    }
}
