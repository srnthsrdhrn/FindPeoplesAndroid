package com.blackpanther.findpeople.Wall;

import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 12/9/16.
 */
public class Post {
    protected String title,description;
    protected List<String> category;
    protected Date created;
    protected List<Likes> likes;
    protected List<Comments> comments;
    protected long n_likes;

    public Post(String project_title, String project_description, Date created, List<String> project_category) {
        title=project_title;
        description=project_description;
        this.created=created;
        category =project_category;

    }
    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return description;///note this
    }

    public List<String> getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public List<Comments> getComments() {
        return comments;
    }
    public long getN_Likes(){
        return n_likes;
    }
    public List<Likes> getLikes() {
        return likes;
    }
}
