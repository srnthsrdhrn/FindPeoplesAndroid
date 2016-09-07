package com.blackpanther.findpeople.profile;

import android.graphics.Bitmap;

/**
 * Created by ubuntu on 5/9/16.
 */
public class NamePic {
    private String Name;
    private Bitmap ProfilePic;

    public NamePic(String name, Bitmap profilePic) {
        Name = name;
        ProfilePic = profilePic;
    }

    public String getName() {
        return Name;
    }

    public Bitmap getProfilePic() {
        return ProfilePic;
    }
}
