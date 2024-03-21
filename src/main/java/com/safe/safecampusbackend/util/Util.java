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

    /**
     * 分开复合id
     *
     * @param compoundId 复合id
     * @return 用户id与内容id的数组，0为用户id
     */
    public static Long[] decompoundId(String compoundId) {
        String[] parts = compoundId.split("_");
        if (parts.length == 2) {
            try {
                Long userId = Long.parseLong(parts[0]);
                Long itemId = Long.parseLong(parts[1]);
                return new Long[]{userId, itemId};
            } catch (NumberFormatException e) {
                // 处理解析异常
                return null;
            }
        } else {
            // 处理非法格式
            return null;
        }
    }
}
