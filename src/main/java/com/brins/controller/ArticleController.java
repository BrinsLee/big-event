package com.brins.controller;

import com.auth0.jwt.JWT;
import com.brins.pojo.Article;
import com.brins.pojo.PageBean;
import com.brins.pojo.Result;
import com.brins.service.ArticleService;
import com.brins.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by lipeilin on 2024/1/22.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/) {
        /*try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("所有的文章数据");
        } catch (Exception e) {
            //http 响应码401
            response.setStatus(401);
            return Result.error("未登录");
        }*/
        return Result.success("所有的文章数据");
    }


    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) String categoryId,
                                          @RequestParam(required = false) String state
    ) {
        PageBean<Article> pageBean = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pageBean);
    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id) {
        Article article = articleService.detail(id);
        System.out.println(article.getId() + " " + article.getTitle() + " id " + id);
        return Result.success(article);
    }

}
