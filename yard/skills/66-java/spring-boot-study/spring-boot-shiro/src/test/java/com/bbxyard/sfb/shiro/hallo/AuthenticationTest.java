package com.bbxyard.sfb.shiro.hallo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void before() {
        simpleAccountRealm.addAccount("admin", "3.14159265");
    }

    @Test
    public void testAuthentication() {
        // #1. 构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // #2. 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin", "3.14159265");
        subject.login(token);
        System.out.println("是否通过认证: " + subject.isAuthenticated());
        subject.logout();
        System.out.println("是否通过认证: " + subject.isAuthenticated());
    }
}
