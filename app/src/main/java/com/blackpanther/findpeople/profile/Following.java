package com.blackpanther.findpeople.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ubuntu on 5/9/16.
 */
public class Following{
    private List<String> myList=new ArrayList<>();

    public Following(List<String> myList) {
        this.myList = myList;
    }

    public List<String> getMyList() {
        return myList;
    }
}
