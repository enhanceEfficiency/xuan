package cn.gsein.xuan.modules.core.security;

import cn.hutool.crypto.digest.MD5;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

/**
 * @author G. Seinfeld
 * @since 2020/06/09
 */
public class Md5CredentialsMatcher extends SimpleCredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        char[] tokenCredentials = (char[]) getCredentials(token);
        String rawCredentials = new String(tokenCredentials);

        if (info instanceof SimpleAuthenticationInfo) {
            SimpleAuthenticationInfo authenticationInfo = (SimpleAuthenticationInfo) info;
            ByteSource credentialsSalt = authenticationInfo.getCredentialsSalt();
            MD5 md5 = new MD5(credentialsSalt.getBytes());
            String encryptedCredentials = md5.digestHex(rawCredentials);

            char[] accountCredentials = (char[]) getCredentials(info);
            return equals(encryptedCredentials.toCharArray(), accountCredentials);
        }
        return false;

    }
}
