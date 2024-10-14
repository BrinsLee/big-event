package com.brins.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by lipeilin on 2024/1/20.
 * lombok 在编译阶段，为实体类自动生成setter，getter toString
 * pom 文件引入依赖，在实体类上添加依赖
 */
@Data
public class User {
    @NotNull
    private Long id;             // 对应 'id'
    private String username;     // 对应 'username'
    @JsonIgnore
    private String password;     // 对应 'password'
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;     // 对应 'nickname'

    @NotEmpty
    @Email
    private String email;        // 对应 'email'
    private String userPic;      // 对应 'user_pic'
    private LocalDateTime createTime; // 对应 'create_time'
    private LocalDateTime updateTime; // 对应 'update_time'

    /*// 构造函数
    public User() {
    }

    // getter 和 setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", userPic='" + userPic + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }*/
}
