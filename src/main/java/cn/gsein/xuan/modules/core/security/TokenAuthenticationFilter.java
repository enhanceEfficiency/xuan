package cn.gsein.xuan.modules.core.security;

import cn.gsein.xuan.modules.common.entity.JsonResult;
import cn.gsein.xuan.modules.common.entity.ResultCode;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author G. Seinfeld
 * @since 2020/06/10
 */
@Slf4j
public class TokenAuthenticationFilter extends AccessControlFilter {

    /**
     * 这里直接返回false，让shiro执行onAccessDenied方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 从token中解析出jwt的字符串
        String token = httpServletRequest.getHeader("Authorization");
        // token格式Bearer XXX
        if (!StringUtils.isEmpty(token) && token.startsWith("Bearer")) {
            token = token.substring(7);
        }
        JwtToken jwtToken = new JwtToken(token);

        // 尝试使用jwtToken登录
        try {
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e) {
            log.warn("登录失败，jwtToken为{}", token);
            onLoginFail(response);
            return false;
        }

        return true;
    }

    private void onLoginFail(ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        try (PrintWriter writer = httpServletResponse.getWriter()) {
            JsonResult<Object> result = JsonResult.get(ResultCode.BAD_REQUEST, "token验证失败", null);
            writer.write(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
