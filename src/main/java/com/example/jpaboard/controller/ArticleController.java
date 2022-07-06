package com.example.jpaboard.controller;

import com.example.jpaboard.dao.ArticleRepository;
import com.example.jpaboard.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }

    @RequestMapping("detail")
    @ResponseBody
    public Article showDetail(long id) {
        Optional<Article> article =  articleRepository.findById(id);
        return article.get();
    }

    @RequestMapping("doModify")
    @ResponseBody
    public Article doModify(long id, String title, String body) {
        Article article =  articleRepository.findById(id).get();

        if (title != null){
            article.setTitle(title);
        }
        if (body != null){
            article.setBody(body);
        }

        articleRepository.save(article);

        return article;
    }

    @RequestMapping("doDelete")
    @ResponseBody
    public String doDelete(long id) {
        articleRepository.deleteById(id);

        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }
}
