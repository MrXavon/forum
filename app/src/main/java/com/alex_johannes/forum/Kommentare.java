package com.alex_johannes.forum;

/**
 * Created by jstoetzel on 18.03.2017.
 */

public class Kommentare {
    public String content;

    private Kommentare(){}

    public Kommentare(String content){
        this.content=content;
    }

    public String getContent() {
        return content;
    }
}
