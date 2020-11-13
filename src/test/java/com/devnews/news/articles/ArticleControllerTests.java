package com.devnews.news.articles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
   Tests are applicable to all classes/packages
 */
@SpringBootTest
public class ArticleControllerTests {

    @Autowired
    ArticleController articleController;

    @MockBean
    ArticleServices articleServices;

    @Test
    public void testGetById() {

        //Arrange
        Article firstArticle = new Article(1L, "Sayf", "endangered animals", "woohoo");
        Article secondArticle = new Article(2L, "Eduardo", "new technologies", "woohoo");

        Mockito.when(articleServices.getById(1L)).thenReturn(Optional.of(firstArticle));
        Mockito.when(articleServices.getById(2L)).thenReturn(Optional.of(secondArticle));

        //Act
        Article controllerFirstArticle = articleController.getById(1L);
        Article controllerSecondArticle = articleController.getById(2L);

        //Assert
        Assertions.assertEquals(firstArticle.getAuthorName(), controllerFirstArticle.getAuthorName());
        Assertions.assertEquals(secondArticle.getAuthorName(), controllerSecondArticle.getAuthorName());

    }

    @Test
    public void testGetAll() {

        //Arrange
        Article firstArticle = new Article(1L, "Sayf", "endangered animals", "woohoo");
        Article secondArticle = new Article(2L, "Eduardo", "new technologies", "woohoo");

        List<Article> articles = new ArrayList<>();
        articles.add(firstArticle);
        articles.add(secondArticle);

        Mockito.when(articleServices.getAll("name")).thenReturn(articles);

        //Act
        List<Article> actualArticles = articleController.getAll(null, null);

        //Assert
        Assertions.assertEquals(articles.size(), actualArticles.size());
        Assertions.assertEquals(articles.get(0).getAuthorName(), actualArticles.get(0).getAuthorName());
        Assertions.assertEquals(articles.get(1).getBody(), actualArticles.get(1).getBody());

    }

    @Test
    public void testGetAllByTopicId() {

        //Arrange
        Long topicId = 23L;
        Article firstArticle = new Article(1L, "Sayf", "endangered animals", "woohoo");
        Article secondArticle = new Article(2L, "Eduardo", "new technologies", "woohoo");

        List<Article> articles = new ArrayList<>();
        articles.add(firstArticle);
        articles.add(secondArticle);

        Mockito.when(articleServices.getAllByTopicId(topicId)).thenReturn(articles);

        //Act
        List<Article> actualArticles = articleController.getAll(null, topicId);


        //Assert
        Assertions.assertEquals(articles.size(), actualArticles.size());
        Assertions.assertEquals(articles.get(0).getAuthorName(), actualArticles.get(0).getAuthorName());
        Assertions.assertEquals(articles.get(1).getBody(), actualArticles.get(1).getBody());

    }

}
