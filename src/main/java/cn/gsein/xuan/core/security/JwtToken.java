package cn.gsein.xuan.core.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 基于jwt的shiro验证token
 *
 * @author G. Seinfeld
 * @since 2020/06/10
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
