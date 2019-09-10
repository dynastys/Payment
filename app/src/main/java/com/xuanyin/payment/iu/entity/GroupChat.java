package com.xuanyin.payment.iu.entity;

import android.graphics.Bitmap;

import com.xuanyin.People;

import java.util.List;

public class GroupChat {

    private String title;
    private String time;
    private List<PeopleGC> peopleGCS;

    public GroupChat(String title,String time,List<PeopleGC> peopleGCS){
        this.title = title;
        this.time = time;
        this.peopleGCS = peopleGCS;

    }

    public String getTitle() {
        return title;
    }



    public String getTime() {
        return time;
    }

    public List<PeopleGC> getPeopleGCS() {
        return peopleGCS;
    }


    public static class PeopleGC{
        private String peopel;
        private int image;
        private boolean drawee;
        private boolean Participants;

        public PeopleGC(String peopel,int image,boolean drawee,boolean Participants){
            this.peopel = peopel;
            this.image = image;
            this.drawee = drawee;
            this.Participants = Participants;
        }

        public String getPeopel() {
            return peopel;
        }

        public int getImage() {
            return image;
        }

        public boolean isDrawee() {
            return drawee;
        }

        public boolean isParticipants() {
            return Participants;
        }
    }


}
