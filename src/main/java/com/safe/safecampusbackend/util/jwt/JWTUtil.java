package com.safe.safecampusbackend.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * JWT 创建静态方法
     *
     * @param subject   用户唯一标识符，admin 使用 username；users 使用 name
     * @param ttlMillis JWT 过期时间
     * @return JWT 字符串
     */
    public static String createJWT(String subject, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + ttlMillis))
                .signWith(key)
                .compact();
    }

    /**
     * JWT 校验
     *
     * @param jwt             JWT
     * @param expectedSubject 用户唯一标识符
     * @return 是否通过验证
     */

    public static boolean validateJWT(String jwt, String expectedSubject) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            Claims body = claimsJws.getBody();
            String actualSubject = body.getSubject();
            Date expiration = body.getExpiration();
            return actualSubject.equals(expectedSubject) && !expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
