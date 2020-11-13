package com.devnews.news.articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServices {

    @Autowired
    private ArticleRepository repo;

    public List<Article> getAll(String sort) {
        return repo.findAll().stream().sorted(Comparator
                .comparing(sort.equals("name") ? Article::getAuthorName : Article::getTitle))
                .collect(Collectors.toList());
    }

    public Optional<Article> getById(Long id) {
        return repo.findById(id);

    }

    public Article createArticle(Article newArticle) {
        return repo.save(newArticle);
    }


    public void deleteArticle(Long id) {
        repo.deleteById(id);
    }

    public Article update(Article updatedArticle) {
        return repo.save(updatedArticle);
    }


    public List<Article> getAllByTopicId(Long topicId) {
        return repo.findAllByTopics_id(topicId);
    }
}
