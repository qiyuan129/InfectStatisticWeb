package com.example.infect_statistic_web.repository;

import com.example.infect_statistic_web.model.Article;

import java.util.List;

public interface ArticleDAO {
    void addArticle(Article article);
    void deleteArticle(int id, String tag);
    Article getArticle(int id);
    void updateArticle(Article article);
    List<Article> getList(String tag);
}
