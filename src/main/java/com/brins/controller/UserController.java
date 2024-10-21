package com.brins.controller;

import com.brins.pojo.Result;
import com.brins.pojo.User;
import com.brins.service.UserService;
import com.brins.utils.JwtUtil;
import com.brins.utils.MD5Util;
import com.brins.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lipeilin on 2024/1/20.
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByUserName(username);
        //判断用户名是否占有
        if (u == null) {
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "\\S{5,16}$") String username, @Pattern(regexp = "\\S{5,16}$") String password) {
        //根据用户名查询User
        User user = userService.findByUserName(username);
        //判断是否查询到
        if (user == null) {
            return Result.error("用户名不存在");
        }
        //判断密码是否正确
        if (MD5Util.md5(password).equals(user.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);

            // 把token存储到redis中
            ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
            stringStringValueOperations.set(token, token, 2, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String , Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(rePwd)) {
            return Result.error("缺少必要参数");
        }
        //原密码是否正确
        //调用userService根据用户名拿到原密码，再和oldPwd比对
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(MD5Util.md5(oldPwd))) {
            return Result.error("密码错误");
        }

        //newPwd和rePwd是否一致
        if (!rePwd.equals(newPwd)) {
            return Result.error("两次密码不一致");
        }

        //newPwd与oldPwd不能一样
        if (newPwd.equals(oldPwd)) {
            return Result.error("新密码不能跟原密码一样");
        }
        // 删除redis 对应的token
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        RedisOperations<String, String> operations = valueOperations.getOperations();
        operations.delete(token);

        //2.调用service完成密码更新
        userService.updatePassword(newPwd);
        return Result.success();

    }

}
