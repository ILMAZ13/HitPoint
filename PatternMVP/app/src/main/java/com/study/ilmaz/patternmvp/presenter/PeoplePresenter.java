package com.study.ilmaz.patternmvp.presenter;


import android.view.View;

import com.study.ilmaz.patternmvp.model.entities.User;
import com.study.ilmaz.patternmvp.model.service.PeopleFactory;
import com.study.ilmaz.patternmvp.view.interfaces.PeopleActivityInterface;

import java.util.ArrayList;
import java.util.List;

public class PeoplePresenter {
    private PeopleActivityInterface activityInterface;
    private List<User> users;

    public PeoplePresenter(PeopleActivityInterface activityInterface) {
        this.activityInterface = activityInterface;
        users = new ArrayList<>();
    }

    public void onLoadButtonPressed(View view) {
        activityInterface.showLoading();
        List<User> temp = PeopleFactory.getUsers(20);
        users.addAll(temp);
        activityInterface.showPeopleList(users);
    }

    public void onItemClicked(User user, View view) {
        activityInterface.navigateToDetailActivity(user, view);
    }
}
