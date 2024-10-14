package com.brins.service;

import com.brins.pojo.Category;

import java.util.List;

/**
 * Created by lipeilin on 2024/1/24.
 */

public interface CategoryService {
    /**
     * 新增文章分类
     * @param category
     */
    void add(Category category);

    /**
     * 列表查询
     * @return
     */
    List<Category> list();

    /**
     * 根据文章分类id查询分类信息
     * @param id
     * @return
     */
    Category findById(Integer id);

    /**
     * 更新文章分类
     * @param category
     */
    void update(Category category);

    /**
     * 删除文章分类
     * @param id
     */
    void delete(Integer id);
}
