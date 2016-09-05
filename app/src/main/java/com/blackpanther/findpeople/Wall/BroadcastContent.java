package com.blackpanther.findpeople.Wall;


import com.blackpanther.findpeople.profile.Profile;

import java.util.List;

/**
 * Created by ubuntu on 5/9/16.
 */
public class BroadcastContent {
    private String username;
    private String description;
    private String brief;
    private String category;
    private String title;
    private List<Likes> likes;
    private List<Comments> comments;

    public BroadcastContent(String username, String description, String brief, String category, String title, List<Likes> likes, List<Comments> comments, Profile participants) {
        this.username = username;
        this.description = description;
        this.brief = brief;
        this.category = category;
        this.title = title;
        this.likes = likes;
        this.comments = comments;
        this.participants = participants;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public List<Comments> getComments() {
        return comments;
    }

    private Profile participants;

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getBrief() {
        return brief;
    }

    public String getCategory() {
        return category;
    }

    public Profile getParticipants() {
        return participants;
    }
}
