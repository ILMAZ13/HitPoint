package com.study.ilmaz.patternmvp.presenter;


import com.study.ilmaz.patternmvp.model.entities.User;
import com.study.ilmaz.patternmvp.view.interfaces.DetailActivityInterface;

public class DetailPresenter {
    private DetailActivityInterface activityInterface;

    public DetailPresenter(DetailActivityInterface activityInterface) {
        this.activityInterface = activityInterface;
    }

    public void onUserExtraLoaded(User user){
        activityInterface.showUserInformation(user);
    }
}
