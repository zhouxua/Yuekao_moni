package com.bwie.yuekao.presenter;

import com.bwie.yuekao.API.Kbean;
import com.bwie.yuekao.model.UserModel;
import com.bwie.yuekao.view.Iview;

/**
 * 周旋
 * 2017/11/23  09:10
 */

public class Iparsenter implements UserModel.OnFinish{
    private final Iview iview;
    private final UserModel userModel;

    public Iparsenter(Iview iview) {
        this.iview = iview;
        this.userModel = new UserModel();
        userModel.setOnFinish(this);
    }
    public void setNews(String url,String pnum){
        userModel.getJson(url,pnum);
    }

    @Override
    public void OnFinishListener(Kbean kbean) {
        iview.getData(kbean);
    }
}
