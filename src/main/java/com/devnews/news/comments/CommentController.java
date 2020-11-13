package com.devnews.news.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentServices commentServices;

    @GetMapping("")
    public List<Comment> getAll(@RequestParam(required = false) Long articleId) {

        if (articleId == null) {
            return commentServices.getAll();
        } else {
            return commentServices.getAllByArticleId(articleId);
        }
    }

    @GetMapping("/{id}")
    public Comment getById(@PathVariable Long id) {
        return commentServices.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Comment create(@RequestBody Comment newComment) {
        return commentServices.createComment(newComment);
    }

    @PutMapping("")
    public Comment update(@RequestBody Comment updatedComment) {
        return commentServices.update(updatedComment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commentServices.deleteComment(id);
    }


}
