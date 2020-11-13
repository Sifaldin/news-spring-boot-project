package com.devnews.news.articles;

import com.devnews.news.comments.Comment;
import com.devnews.news.topics.Topic;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Article {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String authorName;
    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<Topic> topics;



    public Article() {
    }

    public Article(Long id, String authorName, String title, String body) {
        this.id = id;
        this.authorName = authorName;
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String date) {
        this.title = date;
    }

    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
