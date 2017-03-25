package com.alex_johannes.forum;

import java.lang.reflect.Array;

/**
 * Created by jstoetzel on 18.03.2017.
 */

public class Message {
    private String message;
    private String author;
    private Kommentare[] kommentare;

    private Message(){}

    public Message(String msg, String author, Kommentare[] liste){
        this.message=msg;
        this.author=msg;
        this.kommentare=liste;
    }

    public String getMessage(){
        return this.message;
    }

    public  String getAuthor(){
        return  this.author;
    }

    public  Kommentare[] getKommentare(){
        return  this.kommentare;
    }
}
