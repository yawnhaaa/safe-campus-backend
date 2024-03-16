package com.safe.safecampusbackend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    /**
     * 验证邮箱格式静态方法
     *
     * @param email 邮箱
     * @return 邮箱格式的对错
     */
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
