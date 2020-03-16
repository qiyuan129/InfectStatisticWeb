package com.example.infect_statistic_web.model;

import java.time.LocalDate;

public class Article {
    int idCount=0;
    int id;
    String title;   //标题
    String comment;  //内容
    String tag;    //标签，用于归类，如全国/福建/湖北等
    LocalDate lastUpdateTime;    //最后更新时间


    public Article(){
        id=++idCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDate getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDate lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }





}
