package com.devnews.news.topics;

import com.devnews.news.articles.Article;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String topicName;

    @ManyToMany(mappedBy = "topics")
    private List<Article> articles;


    public Topic() {
    }

    public Topic(Long id, String topicName) {
        this.id = id;
        this.topicName = topicName;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }


}
