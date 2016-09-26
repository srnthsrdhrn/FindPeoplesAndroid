package com.blackpanther.findpeople.Wall;

import com.blackpanther.findpeople.profile.Profile;
/**
 * Created by ubuntu on 14/9/16.
 */


public class Likes {
    Profile users;

    public Likes(Profile users) {
        this.users = users;
    }

    public Profile getUsers() {
        return users;
    }
}
