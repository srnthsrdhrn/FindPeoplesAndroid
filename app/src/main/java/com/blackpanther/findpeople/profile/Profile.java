package com.blackpanther.findpeople.profile;

/**
 * Created by ubuntu on 5/9/16.
 */
public class Profile {
    Following following;
    NamePic namePic;
    Skills skills;

    public Profile(Following following, NamePic namePic, Skills skills) {
        this.following = following;
        this.namePic = namePic;
        this.skills = skills;
    }

    public Following getFollowing() {
        return following;
    }

    public NamePic getNamePic() {
        return namePic;
    }

    public Skills getSkills() {
        return skills;
    }
}
