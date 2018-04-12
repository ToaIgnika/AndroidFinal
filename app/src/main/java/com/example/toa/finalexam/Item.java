package com.example.toa.finalexam;

/**
 * Created by Toa on 4/12/2018.
 */

public class Item {
    private String name;
    private String url;

    public Item (String n, String u) {
        name = n;
        url = u;
    }

    public String getName(){
        return name;
    }

    public String getUrl () {
        return url;
    }

}
