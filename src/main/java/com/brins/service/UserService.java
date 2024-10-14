package com.brins.service;

import com.brins.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by lipeilin on 2024/1/20.
 */

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);

    /**
     * 更新密码
     * @param newPwd
     */
    void updatePassword(String newPwd);
}
