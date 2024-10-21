package com.brins.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

/**
 * Created by lipeilin on 2024/1/22.
 */
public class JwtUtil {

    private static final String KEY = "itheima";

    public static String genToken(Map<String, Object> claims) {
        return JWT.create().withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 2))
                .sign(Algorithm.HMAC256(KEY));
    }

    public static Map<String , Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
