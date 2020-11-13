package com.devnews.news.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServices {

    @Autowired
    private CommentRepo repo;

    public List<Comment> getAll() {
        return repo.findAll();

        /*.stream().sorted(Comparator
                .comparing(sort.equals("name") ? Comment::getAuthorName : Comment::getBody))
                .collect(Collectors.toList()); */
    }

    public Optional<Comment> getById(Long id) {
        return repo.findById(id);

    }

    public Comment createComment(Comment newComment) {
        return repo.save(newComment);
    }


    public void deleteComment(Long id) {
        repo.deleteById(id);
    }

    public Comment update(Comment updatedComment) {
        return repo.save(updatedComment);
    }


    public List<Comment> getAllByArticleId(Long articleId) {
        return repo.findAllByArticleId(articleId);
    }
}
