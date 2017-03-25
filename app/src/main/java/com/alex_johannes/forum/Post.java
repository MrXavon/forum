package com.alex_johannes.forum;

/**
 * Created by jstoetzel on 19.03.2017.
 */

public class Post {

    private String titel;
    private String author;
    private String content;

    public Post(String titel, String author, String content) {
        this.titel = titel;
        this.author = author;
        this.content = content;
    }
    public  Post(){}

    public String getTitel() {
        return titel;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
