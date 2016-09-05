package com.blackpanther.findpeople.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 5/9/16.
 */
public class Skills {
    private List<String> myList=new ArrayList<>();

    public Skills(List<String> myList) {
        this.myList = myList;
    }

    public List<String> getMyList() {
        return myList;
    }
}
