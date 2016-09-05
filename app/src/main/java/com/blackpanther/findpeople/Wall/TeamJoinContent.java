package com.blackpanther.findpeople.Wall;

import com.blackpanther.findpeople.profile.Profile;

import java.util.List;

/**
 * Created by ubuntu on 5/9/16.
 */
public class TeamJoinContent{
    String project_title,project_category,project_description,project_brief;
    List<Profile> members;
    private List<Likes> likes;
    private List<Comments> comments;

    public TeamJoinContent(String project_title, String project_category, String project_description, String project_brief, List<Profile> members, List<Likes> likes, List<Comments> comments) {
        this.project_title = project_title;
        this.project_category = project_category;
        this.project_description = project_description;
        this.project_brief = project_brief;
        this.members = members;
        this.likes = likes;
        this.comments = comments;
    }

    public String getProject_title() {
        return project_title;
    }

    public String getProject_category() {
        return project_category;
    }

    public String getProject_description() {
        return project_description;
    }

    public String getProject_brief() {
        return project_brief;
    }

    public List<Profile> getMembers() {
        return members;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public List<Comments> getComments() {
        return comments;
    }
}
