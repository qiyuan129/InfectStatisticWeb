package com.example.infect_statistic_web.model;

import java.time.LocalDate;

public class Article {
    int idCount=0;
    int id;
    String title;   //标题
    String content;  //内容
    String tag;    //标签，用于归类，如全国/福建/湖北等

    public int getId() {
        return id;
    }
    public Article(){
        id=++idCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}
