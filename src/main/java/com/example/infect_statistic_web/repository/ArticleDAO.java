package com.example.infect_statistic_web.repository;

import com.example.infect_statistic_web.model.Article;

public interface ArticleDAO {
    void addArticle(Article article);
    void deleteArticle(int id);
    Article getArticle(int id);
    void updateArticle(Article article);
}
