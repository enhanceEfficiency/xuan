package cn.gsein.xuan.core.security;

import cn.gsein.xuan.common.util.DateUtil;
import cn.hutool.core.codec.Base64;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 处理JWT token的业务层
 *
 * @author G. Seinfeld
 * @since 2020/06/09
 */
@Service
@Slf4j
public class TokenService {

    /**
     * token过期时间 60分钟
     */
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    private static final String DEFAULT_SECRET_KEY = "cn.gsein.xuan";

    @Value("jwt.token.secretKey")
    private String secretKey;

    /**
     * 生成token
     */
    public String generateToken(String username) {
        LocalDateTime time = DateUtil.now().plus(EXPIRE_TIME, ChronoUnit.MILLIS);
        Date expiresAt = DateUtil.toDate(time);

        Algorithm algorithm = getAlgorithm();
        return JWT.create()
                .withClaim("username", username)
                .withIssuedAt(DateUtil.nowDate())
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    /**
     * 验证token是否合法
     */
    public boolean verify(String token) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }


    public DecodedJWT decode(String token) {
        return JWT.decode(token);
    }

    private Algorithm getAlgorithm() {
        String key = secretKey;
        if (StringUtils.isEmpty(secretKey)) {
            key = DEFAULT_SECRET_KEY;
        }
        String base64EncodedKey = Base64.encode(key);

        return Algorithm.HMAC256(base64EncodedKey);
    }

}
