package com.brins.service.impl;

import com.brins.mapper.ArticleMapper;
import com.brins.pojo.Article;
import com.brins.pojo.PageBean;
import com.brins.service.ArticleService;
import com.brins.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by lipeilin on 2024/10/14.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state) {
        // 1、创建PageBean封装对象
        PageBean<Article> pageBean = new PageBean<>();
        // 2、开启分页查询
        // 借助pageHelper插件
        PageHelper.startPage(pageNum, pageSize);

        // 3、调用Mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> articles = articleMapper.list(userId, categoryId, state);
        // Page 中提供了方法可以获取PageHelper分页查询后，得到的总记录条数和当前分页数据
        Page<Article> page = (Page<Article>) articles;
        // 把数据填充到pageBean
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

    @Override
    public Article detail(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Article article = articleMapper.detail(userId, id);
        return article;
    }
}
