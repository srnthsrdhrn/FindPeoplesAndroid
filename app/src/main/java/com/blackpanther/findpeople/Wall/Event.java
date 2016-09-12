package com.blackpanther.findpeople.Wall;

import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 12/9/16.
 */
public class Event extends Post{
    private Date event_date;
    private Date due_date;

    public Event(String project_title, String project_description, Date created, List<String> project_category,Date event_date,Date due_date) {
        super(project_title, project_description, created, project_category);
        this.event_date=event_date;
        this.due_date=due_date;
    }
    public Date getEvent_date(){
        return event_date;
    }
    public Date getDue_date(){
        return due_date;
    }


}
