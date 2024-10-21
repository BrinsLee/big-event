package com.brins.interceptors;

import com.brins.pojo.Result;
import com.brins.utils.JwtUtil;
import com.brins.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * Created by lipeilin on 2024/1/22.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try {
            // 从redis获取相同的token
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            String redisToken = valueOperations.get(token);
            if (redisToken == null) {
                throw new RuntimeException("token不存在");
            }
//            stringRedisTemplate.hasKey(token);
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            System.out.println("验证成功");
            return true;
        } catch (Exception e) {
            //http 响应码401
            response.setStatus(401);
            System.out.println("验证失败" + e.getMessage());
            return false;
        }
    }

    /**
     * 清空ThreadLocal数据
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
