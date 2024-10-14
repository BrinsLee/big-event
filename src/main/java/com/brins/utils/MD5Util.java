package com.brins.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lipeilin on 2024/1/21.
 */
public class MD5Util {
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            return convertByteToHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    private static String convertByteToHex(byte[] byteData) {
        StringBuilder sb = new StringBuilder();
        for (byte b : byteData) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
