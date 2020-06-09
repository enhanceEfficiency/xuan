package cn.gsein.xuan.modules.core.security;

import cn.gsein.xuan.modules.system.user.dao.UserDao;
import cn.gsein.xuan.modules.system.user.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("不允许空用户名");
        }

        SimpleAuthenticationInfo info;
        Optional<User> user = userDao.getUserByUsernameAndDeletedIsFalse(username);
        Optional<String> password = user.map(User::getPassword);

        if (!password.isPresent()) {
            throw new UnknownAccountException("用户名或密码错误");
        } else {
            info = new SimpleAuthenticationInfo(username, password.get().toCharArray(), getName());
        }

        // 如果有盐值，加入info中
        Optional<String> salt = user.map(User::getSalt);
        salt.ifPresent(s -> info.setCredentialsSalt(ByteSource.Util.bytes(s)));


        return info;
    }
}
