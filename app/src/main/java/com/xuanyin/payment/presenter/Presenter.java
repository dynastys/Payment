package com.xuanyin.payment.presenter;

import com.xuanyin.model.Util;
import com.xuanyin.payment.view.ILogView;

public class Presenter implements ILogPresent {

    public Util util;
    private ILogView iLogView;

    public Presenter(ILogView iLogView){
        this.iLogView = iLogView;
        util = new Util();
    }


    @Override
    public void information() {
        iLogView.show();
    }




}
