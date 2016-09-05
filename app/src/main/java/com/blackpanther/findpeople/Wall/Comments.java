package com.blackpanther.findpeople.Wall;

import com.blackpanther.findpeople.profile.Profile;

import java.util.List;

/**
 * Created by ubuntu on 5/9/16.
 */
public class Comments
{
    Profile user;
    String content;
    List<Likes> likes;

    public Comments(Profile user, String content, List<Likes> likes) {
        this.user = user;
        this.content = content;
        this.likes = likes;
    }

    public Profile getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public List<Likes> getLikes() {
        return likes;
    }
}
