package com.devnews.news.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleServices articleServices;

    @GetMapping("")
    public List<Article> getAll(@RequestParam(required = false) String sort, @RequestParam(required = false) Long topicId) {
        if (sort == null) {
            sort = "name";
        }
        if (topicId != null) {
            return articleServices.getAllByTopicId(topicId);
        } else {
            return articleServices.getAll(sort);
        }
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable Long id) {
        return articleServices.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /*
    curl -X POST -d '"article":{"id":1,"title":"10 reasons to learn Spring","body":"In this article I'll be listin project...","authorName":"John Smith"}' -H "Content-Type: application/json" localhost:8080/comments
        curl -X POST -d '{"id": 1,"body":"This article is very well written","authorName":"John Smith","article":{"id":1,"title":"10 reasons to learn Spring","body": "In this article I'll be listing 10 reasons why you should learn spring and use it in your next project...","authorName": "John Smith"}}' -H "Content-Type: application/json" localhost:8080/comments
        curl -X PUT -d '{"id": 2,"title":"World","authorName":"John Smith","comment":{"id":1}}' -H "Content-Type: application/json" localhost:8080/articles
        curl -X POST -d '{"title":"comment 1","authorName":"sayf"}' -H "Content-Type: application/json" localhost:8080/comments
        curl -X PUT -d '{"id":1,"authorName":"John Smith","title":"Hello","body":null,"comment":[{"id":1},{"id":2}]},' -H "Content-Type: application/json" localhost:8080/articles



    */
    @PostMapping("")
    public Article create(@RequestBody Article newArticle) {
        return articleServices.createArticle(newArticle);
    }

    @PutMapping("")
    public Article update(@RequestBody Article updatedArticle) {
        return articleServices.update(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleServices.deleteArticle(id);
    }


}
