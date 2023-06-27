package com.ccww.aiotlab.common.utils;


import com.ccww.aiotlab.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */

@Slf4j
@Component
@ConfigurationProperties(prefix = "token")
public class JwtTokenUtil implements Serializable {

    //24小时过期时间 86400
    @Setter
    private long expire;

    /**
     * 密钥
     */
    @Setter
    private String secret;

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expire * 1000);
//        log.info(expirationDate.toString());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
//            System.out.println(e);
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌

     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>(4);

        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("kind", user.getKind());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public User getUserFromToken(String token) {
        User user = new User();
        try {
            Claims claims = getClaimsFromToken(token);
            user.setId(claims.get("id",Integer.class));
            user.setEmail(claims.get("email",String.class));
            user.setKind(claims.get("kind",Integer.class));
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }



    /**
     * 验证令牌
     *
     */
    public Boolean validateToken(String token, User user) {
        return ( !isTokenExpired(token) && getUserFromToken(token).getEmail().equals(user.getEmail()));
    }

}
