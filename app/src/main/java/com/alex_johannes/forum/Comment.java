package com.alex_johannes.forum;

/**
 * Created by jstoetzel on 18.03.2017.
 */

public class Comment {
    private String content;
    private String author;

    private Comment(){}

    public Comment(String content, String author){
        this.content=content;
        this.author=author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor(){
        return author;
    }

}
