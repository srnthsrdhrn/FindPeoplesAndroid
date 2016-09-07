package com.blackpanther.findpeople.Wall;

import java.util.List;

/**
 * Created by ubuntu on 3/9/16.
 */
public class WallProjectContent {
    String project_title, project_brief,project_category, project_description;
    List<Likes> likes;
    List<Comments> comments;

    public WallProjectContent(String project_title, String project_brief, String project_category, String project_description, List<Likes> likes, List<Comments> comments) {
        this.project_title = project_title;
        this.project_brief = project_brief;
        this.project_category = project_category;
        this.project_description = project_description;
        this.likes = likes;
        this.comments = comments;
    }

    public String getProject_title() {
        return project_title;
    }

    public String getProject_brief() {
        return project_brief;
    }

    public String getProject_category() {
        return project_category;
    }

    public String getProject_description() {
        return project_description;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public List<Likes> getLikes() {
        return likes;
    }
}
