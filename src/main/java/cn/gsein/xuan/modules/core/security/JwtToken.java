package cn.gsein.xuan.modules.core.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author G. Seinfeld
 * @since 2020/06/10
 */
public class JwtToken implements AuthenticationToken {

    private String token;

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
