package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleApiControllerTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "Seoul", "1111");
        Article b = new Article(2L, "Daegu", "2222");
        Article c = new Article(3L, "Busan", "3333");
        List<Article> excepted = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 실제
        List<Article> articles = articleService.index();
        // 비교
        assertEquals(excepted.toString(), articles.toString());
    }

    @Test
    void show_success() { // 존재하는 아이디 입력
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "Seoul", "1111");
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_fail() { // 존재하지 않는 아이디 입력
        // 예상
        Long id = -1L;
        Article expected = null;
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected, article);
    }
    @Transactional
    @Test
    void create_success() { // title과 content만 있는 dto 입력
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }
    @Transactional
    @Test
    void create_fail() { // id가 포함된 dto 입력
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected, article);
    }
    @Transactional
    @Test
    void update_success() { // 존재하는 id와 title만 있는 dto 입력
        // 예상
        Long id = 1L;
        String title = "Seoul";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void update_fail_존재하지_않는_id의_dto_입력() {
        // 예상
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected, article);
    }

    @Transactional
    @Test
    void update_fail_id만_있는_dto_입력() {
        // 예상
        Long id = 1L;
        String title = "";
        String content = "";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected, article);
    }


    @Transactional
    @Test
    void delete_success() {
        // 예상
        Long id = 1L;
        String title = "Seoul";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void delete_fail() {
        // 예상
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertEquals(expected, article);
    }
}