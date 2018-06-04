package com.study.ilmaz.patternmvp.model.service;


import com.study.ilmaz.patternmvp.model.entities.Login;
import com.study.ilmaz.patternmvp.model.entities.Name;
import com.study.ilmaz.patternmvp.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class PeopleFactory {
    private static int pos = 0;
    public static List<User> getUsers(int count){
        List<User> users = new ArrayList<>();
        for (int i = pos; i < pos + count; i++) {
            User temp = new User();
            Name name = new Name();
            name.setFirst("Test User");
            name.setLast("Number " + i);
            temp.setName(name);
            temp.setPhone(Long.toString(9876543 * i));
            temp.setGender(getRandom() ? "male" : "female");
            Login login = new Login();
            login.setUsername("usr" + Integer.toString(12345 * i));
            temp.setEmail("panda" + i + "@ilmail.com");
            temp.setLogin(login);

            users.add(temp);
        }
        pos += count;
        return users;
    }

    private static boolean getRandom(){
        int random = (int) Math.abs(Math.round(Math.random() + 10));
        return random % 2 == 0;
    }
}
