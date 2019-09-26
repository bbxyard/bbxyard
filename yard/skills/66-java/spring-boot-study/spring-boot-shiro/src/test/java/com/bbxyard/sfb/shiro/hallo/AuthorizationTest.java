package com.bbxyard.sfb.shiro.hallo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;


public class AuthorizationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void before() {
        simpleAccountRealm.addAccount("boss1", "820974944", "admin", "leader", "user");
        simpleAccountRealm.addAccount("demo1", "59230781", "test", "demo");
    }

    @Test
    public void testAuthorization() {
        // #1. 构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);

        // #2. 主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        // subject.checkRoles("admin", "user"); 同时检验多个写法.

        // #3. 鉴权
        tryVerifyRoles(subject, "demo1", "59230781", new String[]{"demo", "test"});
        tryVerifyRoles(subject, "boss1", "820974944", new String[]{"admin", "leader", "user"});
        tryVerifyRoles(subject, "boss1", "820974944", new String[]{"demo", "user"});
    }

    private void tryVerifyRoles(Subject subject, String username, String password, String[] roles) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            System.out.println("是否通过认证: " + subject.isAuthenticated());
            // #. 验证权限
            for (String role : roles) {
                System.out.printf("Verify User <%s> Has Role <%s>\n", username, role);
                subject.checkRole(role);
            }
            subject.logout();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (UnauthorizedException e) {
            System.err.println("NOT Authorized: " + e.toString());
        }
    }
}
