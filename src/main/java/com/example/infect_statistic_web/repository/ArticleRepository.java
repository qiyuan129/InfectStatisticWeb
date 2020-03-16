package com.example.infect_statistic_web.repository;

import com.example.infect_statistic_web.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Repository
public class ArticleRepository implements ArticleDAO{
    HashMap<String, ArrayList<Article>> articleRepository;

    ArticleRepository(){
        articleRepository=new HashMap<>();
        ArrayList<Article> list=new ArrayList<>();
        articleRepository.put("全国",list);

        Article article=new Article();
        article.setTag("全国");
        article.setTitle("测试文章标题一");
        article.setContent("测试文章内容");
        list.add(article);

        Article article1=new Article();
        article1.setTag("全国");
        article1.setTitle("测试文章标题二");
        article1.setContent("测试文章二内容");
        list.add(article1);
    }
    public Set<String> getTags(){
        return articleRepository.keySet();
    }

    @Override
    public void addArticle(Article article) {
        String tag=article.getTag();
        //如果仓库中没有该标签的文章列表,就新建对应的列表
        ArrayList<Article> list=articleRepository.get(tag);
        if(list==null){
            list=new ArrayList<>();
            articleRepository.put(tag,list);
            list.add(article);
        }
        else{
            list.add(article);
        }
    }

    @Override
    public void deleteArticle(int id, String tag) {
        ArrayList<Article> list=articleRepository.get(tag);
        for(Article article:list){
            if(article.getId()==id){
                list.remove(article);
                break;
            }
        }
    }

    @Override
    public Article getArticle(int id) {
        return null;
    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public List<Article> getList(String tag) {
        ArrayList<Article> list=articleRepository.get(tag);
        return list;
    }
}
