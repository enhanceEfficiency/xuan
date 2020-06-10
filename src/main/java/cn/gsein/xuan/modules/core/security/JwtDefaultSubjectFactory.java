package cn.gsein.xuan.modules.core.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author G. Seinfeld
 * @since 2020/06/10
 */
public class JwtDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        // 禁止创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
