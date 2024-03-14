package com.safe.safecampusbackend.util.salt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

public class SaltUtil {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    /**
     * 产盐
     *
     * @return 盐值
     */
    public static String generateSalt() {
        byte[] salt = new byte[32];
        secureRandom.nextBytes(salt);
        return bytesToHex(salt);
    }

    /**
     * 密码与盐混合生成哈希
     *
     * @param passwd 密码
     * @param salt   盐
     * @return 混合盐值
     */
    public static String fixedSalt(String passwd, String salt) {
        String input = passwd + salt;
        byte[] hashBytes = messageDigest.digest(input.getBytes());
        return bytesToHex(hashBytes); // 返回十六进制字符串的哈希值
    }

    /**
     * 输入密码与盐，检测密码是否正确
     *
     * @param passwd 密码
     * @param salt   盐
     * @return 验证是否正确
     */
    public static Boolean verifySalt(String passwd, String salt, String fixedSalt) {
        String verifyPasswd = fixedSalt(passwd, salt);
        return Objects.equals(verifyPasswd, fixedSalt);
    }

    /**
     * 将字节数组转换成十六进制字符串
     *
     * @param bytes 传入的字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
