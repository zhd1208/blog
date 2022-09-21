package com.mszlu.blog.utils;

import java.util.Random;

public class NumUtil {
    public static String createCode() {
        Random r = new Random();
        char[] tmp = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int pos = r.nextInt(tmp.length);
            char c = tmp[pos];
            code.append(c);
        }
        return code.toString();
    }

}
