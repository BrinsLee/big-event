package com.brins.pojo;

/**
 * Created by lipeilin on 2024/1/20.
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {

    @NotNull(groups = Update.class)
    private Integer id;                // 对应 'id'
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryName;    // 对应 'category_name'
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryAlias;   // 对应 'category_alias'
    private Integer createUser;        // 对应 'create_user'，外键引用

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; // 对应 'create_time'
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 对应 'update_time'

    // 如果某个校验项没有指定分组，默认属于 default 分组
    // 分组间可以继承，A extends B，那么A拥有B中所有的校验项
    public interface Add extends Default {

    }

    public interface Update extends Default{

    }

    /*// 构造函数
    public Category() {
    }

    // getter 和 setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    // toString 方法，可选
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryAlias='" + categoryAlias + '\'' +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }*/
}

