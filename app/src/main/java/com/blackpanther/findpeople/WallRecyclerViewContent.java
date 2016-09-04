package com.blackpanther.findpeople;

/**
 * Created by ubuntu on 3/9/16.
 */
public class WallRecyclerViewContent {
    String project_title,project_description,project_category,project_full_story;
    int likes;
    WallComments comments;


    public class WallComments
    {
        String username,description;

        public WallComments(String username, String description) {
            this.username = username;
            this.description = description;
        }
    }


    public String getProject_title() {
        return project_title;
    }

    public void setProject_title(String project_title) {
        this.project_title = project_title;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProject_category() {
        return project_category;
    }

    public void setProject_category(String project_category) {
        this.project_category = project_category;
    }

    public String getProject_full_story() {
        return project_full_story;
    }

    public void setProject_full_story(String project_full_story) {
        this.project_full_story = project_full_story;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public WallComments getComments() {
        return comments;
    }

    public void setComments(WallComments comments) {
        this.comments = comments;
    }


}
