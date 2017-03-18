package com.alex_johannes.forum;

/**
 * Created by jstoetzel on 18.03.2017.
 */

public class Message {
    private String message;

    private Message(){}

    public Message(String msg){
        this.message=msg;
    }

    public String getMessage(){
        return this.message;
    }
}
