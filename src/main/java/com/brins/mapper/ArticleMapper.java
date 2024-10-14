package com.brins.mapper;

import com.brins.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lipeilin on 2024/10/14.
 */
@Mapper
public interface ArticleMapper {


    /**
     * 新增文章
     * @param article
     */
    @Insert("insert into article (title, content, cover, state, category_id, create_user, " +
            "create_time, update_time) values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, " +
            "#{createUser}, #{createTime}, #{updateTime});")
    void add(Article article);
}
