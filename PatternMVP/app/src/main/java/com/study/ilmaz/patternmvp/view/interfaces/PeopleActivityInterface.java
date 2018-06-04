package com.study.ilmaz.patternmvp.view.interfaces;


import android.view.View;

import com.study.ilmaz.patternmvp.model.entities.User;

import java.util.List;

public interface PeopleActivityInterface {
    void showPeopleList(List<User> userList);
    void showLoading();
    void navigateToDetailActivity(User user, View view);
}
