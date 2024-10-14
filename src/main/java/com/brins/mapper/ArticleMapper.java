package com.brins.mapper;

import com.brins.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 获取文章列表
     * 使用映射配置文件写动态SQL
     * @param userId
     * @param categoryId
     * @param state
     * @return
     */
    List<Article> list(Integer userId, String categoryId, String state);

    /**
     * 获取文章详情
     *
     * @param userId
     * @param id
     * @return
     */
    @Select("select * from article where create_user = #{userId} and id = #{id};")
    @Results({@Result(property = "coverImg", column = "cover")})
    Article detail(Integer userId, Integer id);
}
