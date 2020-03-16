package com.example.infect_statistic_web.web;

import com.example.infect_statistic_web.model.Article;
import com.example.infect_statistic_web.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository localRepository;

    @GetMapping("/article/add")
    public String addArticlePage(){
        return "addArticle";
    }

    @PostMapping("/article/add")
    public String addArticle(Article article){
        localRepository.addArticle(article);
        return "redirect:/articles";
    }

    @RequestMapping("/articles")
    public String articleManagement(Model map,String tag){
        if(tag==null){
            tag="全国";
        }
        map.addAttribute("tags",localRepository.getTags());
        map.addAttribute("articles",localRepository.getList(tag));
        return "articleList";
    }

    @PostMapping("/article/delete")
    public String deleteArticle(int id,String tag){
        localRepository.deleteArticle(id,tag);
        return "redirect:/articles";
    }
}
