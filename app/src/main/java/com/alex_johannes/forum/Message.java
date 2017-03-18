package com.alex_johannes.forum;

/**
 * Created by jstoetzel on 18.03.2017.
 */

public class Message {
    private String message;
    private String author;

    private Message(){}

    public Message(String msg, String author){
        this.message=msg;
        this.author=msg;
    }

    public String getMessage(){
        return this.message;
    }

    public  String getAuthor(){
        return  this.author;
    }
}
