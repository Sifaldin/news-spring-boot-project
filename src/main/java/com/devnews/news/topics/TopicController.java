package com.devnews.news.topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicServices topicServices;

    @GetMapping("")
    public List<Topic> getAll(@RequestParam(required = false) Long articleId) {

        if (articleId != null) {
            return topicServices.getAllByArticleId(articleId);
        } else {
            return topicServices.getAll();
        }
    }

    @GetMapping("/{id}")
    public Topic getById(@PathVariable Long id) {
        return topicServices.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Topic create(@RequestBody Topic newTopic) {
        return topicServices.createTopic(newTopic);
    }

    @PutMapping("")
    public Topic update(@RequestBody Topic updatedTopic) {
        return topicServices.update(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        topicServices.deleteTopic(id);
    }


}
