package com.devnews.news.comments;

import com.devnews.news.articles.Article;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String authorName;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    private Article article;

    public Comment() {
    }

    public Comment(Long id, String authorName, String body) {
        this.id = id;
        this.authorName = authorName;
        this.body = body;

    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
