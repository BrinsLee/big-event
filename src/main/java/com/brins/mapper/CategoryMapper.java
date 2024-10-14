package com.brins.mapper;

import com.brins.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by lipeilin on 2024/1/24.
 */
@Mapper
public interface CategoryMapper {

    /**
     * 新增文章分类
     * @param category
     */
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)" +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    /**
     * 查询当前用户创建的所有分类
     * @param userId
     * @return
     */
    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    /**
     * 根据文章分类id查询
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category findById(Integer id);

    /**
     * 更新文章分类
     * @param category
     */
    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = #{updateTime} where id=#{id}")
    void update(Category category);

    /**
     * 删除文章分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(Integer id);
}
