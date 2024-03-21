package com.safe.safecampusbackend.util;

import java.util.Random;
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

    /**
     * 取得 6 位验证码（000000 - 999999）
     *
     * @return 6位验证码
     */
    public static String getCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000); // 生成 [0, 999999] 范围内的随机数
        return String.format("%06d", randomNumber); // 补齐到六位数并转换为字符串
    }

    /**
     * 复合用户id与内容id
     *
     * @param userId 用户id
     * @param itemId 内容id
     * @return 复合id
     */
    public static String compoundId(String userId, String itemId) {
        return userId + '_' + itemId;
    }
}
