package com.brins.service.impl;

import com.brins.mapper.UserMapper;
import com.brins.pojo.User;
import com.brins.service.UserService;
import com.brins.utils.MD5Util;
import com.brins.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by lipeilin on 2024/1/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //密码加密处理
        String md5String = MD5Util.md5(password);
        //注册
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePassword(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePassword(MD5Util.md5(newPwd), id);
    }
}
