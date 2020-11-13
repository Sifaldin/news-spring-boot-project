package com.devnews.news.topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServices {

    @Autowired
    private TopicRepo repo;

    public List<Topic> getAll() {
        return repo.findAll();
    }

    public Optional<Topic> getById(Long id) {
        return repo.findById(id);
    }

    public Topic createTopic(Topic newTopic) {
        return repo.save(newTopic);
    }


    public void deleteTopic(Long id) {
        repo.deleteById(id);
    }

    public Topic update(Topic updatedTopic) {
        return repo.save(updatedTopic);
    }


    public List<Topic> getAllByArticleId(Long articleId) {
        return repo.findAllByArticles_id(articleId);
    }
}
