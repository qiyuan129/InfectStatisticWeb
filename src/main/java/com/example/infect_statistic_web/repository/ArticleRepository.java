package com.example.infect_statistic_web.repository;

import com.example.infect_statistic_web.model.Article;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleRepository implements ArticleDAO{
    HashMap<String, ArrayList<Article>> articleRepository;
    @Override
    public void addArticle(Article article) {

    }

    @Override
    public void deleteArticle(int id) {

    }

    @Override
    public Article getArticle(int id) {
        return null;
    }

    @Override
    public void updateArticle(Article article) {

    }
}
