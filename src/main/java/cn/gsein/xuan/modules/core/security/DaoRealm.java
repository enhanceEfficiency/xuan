package cn.gsein.xuan.modules.core.security;

import cn.gsein.xuan.modules.system.user.dao.UserDao;
import cn.gsein.xuan.modules.system.user.entity.User;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 基于Dao的Shiro数据源
 *
 * @author G. Seinfeld
 * @since 2020/06/09
 */
public class DaoRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    @Resource
    private TokenService tokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String jwtToken = (String) token.getPrincipal();
        if (StringUtils.isEmpty(jwtToken)) {
            throw new AccountException("不允许空token");
        }

        if (!tokenService.verify(jwtToken)) {
            throw new UnknownAccountException("token不合法");
        }

        DecodedJWT decodedJWT = tokenService.decode(jwtToken);
        String username = decodedJWT.getClaim("username").asString();

        // 查询数据库判断用户是否存在
        Optional<User> user = userDao.getUserByUsernameAndDeletedIsFalse(username);
        if (!user.isPresent()) {
            throw new UnknownAccountException("用户不存在");
        } else {
            return new SimpleAuthenticationInfo(username, token.getCredentials(), getName());
        }

    }
}
