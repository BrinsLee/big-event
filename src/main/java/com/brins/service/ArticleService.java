package com.brins.service;

import com.brins.pojo.Article;
import com.brins.pojo.PageBean;

/**
 * Created by lipeilin on 2024/10/14.
 */
public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void add(Article article);

    /**
     * 条件分类列表查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    Article detail(Integer id);
}
