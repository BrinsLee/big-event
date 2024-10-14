package com.brins.pojo;

/**
 * Created by lipeilin on 2024/1/20.
 */
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;                // 对应 'id'
    private String title;           // 对应 'title'
    private String content;         // 对应 'content'
    private String coverImg;        // 对应 'cover_img'
    private String state;           // 对应 'state'
    private Integer categoryId;        // 对应 'category_id', 外键引用
    private Integer createUser;        // 对应 'create_user', 外键引用
    private LocalDateTime createTime; // 对应 'create_time'
    private LocalDateTime updateTime; // 对应 'update_time'

    // 构造函数
    /*public Article() {
    }

    // getter 和 setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", state='" + state + '\'' +
                ", categoryId=" + categoryId +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }*/
}

