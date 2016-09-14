package com.blackpanther.findpeople.Wall;

import java.util.Date;
import java.util.List;

public class Team extends Post {
    long n_request;
    String status;
    public Team(String project_title, String project_description, Date created, List<String> project_category,long n_request,String status) {
        super(project_title, project_description, created, project_category);
        this.n_request=n_request;
        this.status=status;

    }
    public String getStatus(){
        return status;
    }
    public long getN_request(){
        return n_request;
    }

}
