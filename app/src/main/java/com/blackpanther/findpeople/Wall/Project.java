package com.blackpanther.findpeople.Wall;

import java.util.Date;
import java.util.List;


public class Project extends Post{
    private Date start_date,end_date;


    public Project(String project_title, String project_description,Date created,List<String>project_category, Date start_date,Date end_date) {
        super(project_title,project_description,created,project_category);
        this.start_date=start_date;
        this.end_date=end_date;
    }

    public Date getStart_date(){
        return start_date;
    }
    public Date getEnd_date(){
        return end_date;
    }
}
