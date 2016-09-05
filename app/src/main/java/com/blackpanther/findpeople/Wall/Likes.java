package com.blackpanther.findpeople.Wall;

import com.blackpanther.findpeople.profile.Profile;

import java.util.List;


/**
 * Created by ubuntu on 5/9/16.
 */
public class Likes {
    List<Profile> users;

    public Likes(List<Profile> users) {
        this.users = users;
    }

    public List<Profile> getUsers() {
        return users;
    }
}
