package com.xuanyin;

import org.litepal.crud.LitePalSupport;

public class Demo extends LitePalSupport {


    private String groupname;
    private String data;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
