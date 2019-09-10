package com.xuanyin.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.util.Log;

import com.xuanyin.ConsumptionDetails;
import com.xuanyin.Consumptions;
import com.xuanyin.Demo;
import com.xuanyin.People;
import com.xuanyin.User;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.AddressBook;
import com.xuanyin.payment.iu.entity.Consumption;
import com.xuanyin.payment.iu.entity.Details;
import com.xuanyin.payment.iu.entity.Friend;
import com.xuanyin.payment.iu.entity.Friends;
import com.xuanyin.payment.iu.entity.GroupChat;
import com.xuanyin.payment.iu.entity.Information;
import com.xuanyin.payment.iu.entity.MultipleItem;
import com.xuanyin.payment.iu.entity.Note;
import com.xuanyin.payment.iu.entity.PhoneDto;
import com.xuanyin.payment.iu.entity.Team;
import com.xuanyin.payment.utils.All;
import com.xuanyin.payment.utils.PhoneUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Util {

    private List<Information> utilslist;
    private List<Friend> friendList;
    private List<Details> detailsList;
    private List<Team> teams;
    private List<Consumption> consumptionList;
    private List<GroupChat> groupChatList;
    private List<GroupChat.PeopleGC> peopleGCList;
    private List<Note> noteList;
    private List<AddressBook> addressBookList;
    private List<Friends> friendsList;
    private List<MultipleItem> multipleItem;
    private List<MultipleItem.Multiple> multiples;

    public Util() {
        utilslist = new ArrayList<>();
        friendList = new ArrayList<>();
        detailsList = new ArrayList<>();
        teams = new ArrayList<>();
        consumptionList = new ArrayList<>();
        groupChatList = new ArrayList<>();
        peopleGCList = new ArrayList<>();
        noteList = new ArrayList<>();
        addressBookList = new ArrayList<>();
        friendsList = new ArrayList<>();
        multipleItem = new ArrayList<>();
        multiples = new ArrayList<>();
    }

    public List<Information> data(Context context) {
        for (int i = 0; i < 10; i++) {
            Information information = new Information();
            information.setName("最爱李知恩" + i);
            Bitmap[] bitmaps = new Bitmap[4];
            for (int j = 0; j < 4; j++) {
                bitmaps[j] = BitmapFactory.decodeResource(context.getResources(), R.mipmap.nice);
                information.setImage(bitmaps);
            }
            utilslist.add(information);
        }
        return utilslist;
    }

    public List<Friend> dataBook(Context context) {
        Resources r = context.getResources();
        Friend addressBook = new Friend();
        Bitmap bmp = BitmapFactory.decodeResource(r, R.mipmap.img_default_head);
        addressBook.setBitmap(bmp);
        addressBook.setName("通讯录");
        friendList.add(addressBook);
        for (int i = 0; i < 10; i++) {
            Bitmap bmps = BitmapFactory.decodeResource(r, R.mipmap.pass);
            Friend addressBooks = new Friend();
            addressBook.setBitmap(bmps);
            addressBook.setName("李知恩");
            friendList.add(addressBooks);
        }
        return friendList;
    }

    public List<AddressBook> dataAddressBook(Context context) {
        return PhoneUtil.getPhoneUtil(context).getPhone();
    }

    public List<Details> dataDetails() {
        detailsList.clear();
        for (int i = 0; i < 4; i++) {
            Details details = new Details();
            details.setName("金智媛");
            details.setBitmap(R.mipmap.timg);
            detailsList.add(details);
        }
        return detailsList;
    }



    public List<People> dataConsumption() {
        List<People> people = LitePal.findAll(People.class);
        return people;
    }

    public List<GroupChat> datagroupChat() {
        List<Demo> demoList = LitePal.findAll(Demo.class);
        List<People> peopleList = LitePal.findAll(People.class);
        for (int i = 0; i < demoList.size(); i++) {
            String name = demoList.get(i).getGroupname();
            for (int j = 0; j < peopleList.size(); j++) {
                if (name.equals(peopleList.get(j).getGroupname())) {
                    GroupChat.PeopleGC peopleGC = new GroupChat.PeopleGC(peopleList.get(j).getName(), peopleList.get(j).getImage(),peopleList.get(i).isDrawee(),peopleList.get(i).isParticipants());
                    peopleGCList.add(peopleGC);
                }
            }
            GroupChat groupChat = new GroupChat(demoList.get(i).getGroupname(), demoList.get(i).getData(), peopleGCList);
            groupChatList.add(groupChat);
        }
        return groupChatList;
    }

//    public List<Note> dataNote() {
//
//        int[] image = {R.mipmap.icon_shouru_type_qita, R.mipmap.icon_zhichu_type_canyin, R.mipmap.icon_jiaotongchuxing,
//                R.mipmap.jiushui, R.mipmap.icon_zhichu_type_shuiguolingshi, R.mipmap.xiaochi, R.mipmap.yifu,
//                R.mipmap.richangyongpin, R.mipmap.fangzu, R.mipmap.icon_add_17, R.mipmap.bianji
//
//        };
//        String[] name = {
//                "一般", "餐饮", "交通", "酒水", "水果", "零食", "衣服鞋包", "生活用品", "房租", "电影", "编辑",
//        };
//
//        for (int i = 0; i < image.length; i++) {
//            Note note = new Note();
//            note.setBitmap(image[i]);
//            note.setName(name[i]);
//            noteList.add(note);
//        }
//        return noteList;
//    }

    public List<AddressBook> book() {
        List<Demo> demoList = LitePal.findAll(Demo.class);
        List<People> peopleList = LitePal.findAll(People.class);

        for (int i = 0; i < demoList.size(); i++) {
            demoList.get(i).getGroupname();
        }

        return addressBookList;
    }

    public List<MultipleItem> getnews(){
        List<Consumptions> consumptions= LitePal.findAll(Consumptions.class);
        List<ConsumptionDetails> details = LitePal.findAll(ConsumptionDetails.class);
        for (int i = 0; i < consumptions.size(); i++) {
            MultipleItem multipleItems = new MultipleItem();
            multipleItems.setType(consumptions.get(i).getType());
            multipleItems.setNews_group_name(consumptions.get(i).getGroup_name());
            multipleItems.setNews_text_team_type(consumptions.get(i).getType_name());
            multipleItems.setNews_img_team_type(consumptions.get(i).getType_img());
            multipleItems.setNews_text_team_money(consumptions.get(i).getMoney());
            multipleItems.setNews_team_data(consumptions.get(i).getData());
            multipleItems.setNews_text_team_people(consumptions.get(i).getDrawee());
            multipleItems.setNews_team_remarks(consumptions.get(i).getRemarks());
            for (int j = 0; j < details.size(); j++) {
                MultipleItem.Multiple multiple = new MultipleItem.Multiple(details.get(j).getName(),details.get(j).getTime());
                multiples.add(multiple);
            }
            multipleItems.setMultiples(multiples);
            multipleItem.add(multipleItems);
        }

        return multipleItem;
    }

}
