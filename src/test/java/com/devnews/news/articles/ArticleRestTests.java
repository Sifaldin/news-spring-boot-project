package com.devnews.news.articles;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

/*
   Tests are applicable to all classes/packages
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleRestTests {
    @Autowired
    ArticleServices articleServices;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testCreate() {

        //Arrange
        Article requestArticle = new Article(null, "sayf", "animals", "wohoo");

        //Act
        Article responseArticle = testRestTemplate.postForObject("/articles", requestArticle, Article.class);

        /*
                               Alternative 1

        HttpEntity<Product> response = testRestTemplate.postForEntity("/products", requestProduct, Product.class);
        Product responseProduct = response.getBody();

                               Alternative 2

        HttpEntity<Product> requestProductHttpEntity = new HttpEntity<>(requestProduct);
        HttpEntity<Product> response = testRestTemplate.exchange("/products", HttpMethod.POST, requestProductHttpEntity, Product.class);
        Product responseProduct = response.getBody();
         */

        //Assert
        Assertions.assertEquals(requestArticle.getAuthorName(), responseArticle.getAuthorName());
        Assertions.assertEquals(requestArticle.getTitle(), responseArticle.getTitle());

        //Check if article was added
        Article getResponseArticleById = testRestTemplate.getForObject("/articles/" + responseArticle.getId()
                .toString(), Article.class);
        Assertions.assertEquals(requestArticle.getAuthorName(), getResponseArticleById.getAuthorName());
        Assertions.assertEquals(requestArticle.getTitle(), getResponseArticleById.getTitle());

        //Clean up (sometimes if you don't delete such calls they might effect the following tests)
        testRestTemplate.delete("/articles" + responseArticle.getId().toString());

    }

    @Test
    public void testGetAllReturnEmptyArray() {
        //Act
        String responseArticle = testRestTemplate.getForObject("/articles", String.class);
        //Assert
        Assertions.assertEquals("[]", responseArticle);
    }

    @Test
    public void testUpdate() {

        //Arrange
        Article originalArticle = articleServices
                .createArticle(new Article(null, "sayf", "animals", "wohoo"));
        Article updatedArticle = new Article(originalArticle
                .getId(), "sayf", "updated animals", "updated wohoo");

        //Act
        Article responseArticle = putForArticle(updatedArticle);

        //Assert
        Assertions.assertEquals(updatedArticle.getTitle(), responseArticle.getTitle());
        Assertions.assertEquals(updatedArticle.getBody(), responseArticle.getBody());

        Article getResponseArticleById = testRestTemplate.getForObject("/articles/" + responseArticle
                .getId().toString(), Article.class);
        Assertions.assertEquals(updatedArticle.getTitle(), getResponseArticleById.getTitle());

    }

    private Article putForArticle(Article requestBody) {

        HttpEntity<Article> requestEntity = new HttpEntity<>(requestBody);
        HttpEntity<Article> response = testRestTemplate
                .exchange("/articles", HttpMethod.PUT, requestEntity, Article.class);
        return response.getBody();
    }

}
