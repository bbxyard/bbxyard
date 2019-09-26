package com.bbxyard.sfb.shiro.hallo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class IniRealmTest {

    @Test
    public void test() {
        // 构造realm
        IniRealm realm = new IniRealm("classpath:user.ini");

        // 构建manager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        tryVerify(subject, "user1", "mima1234", "test", new String[]{"user:list"});
        tryVerify(subject, "user2", "mimaAB", "admin", new String[]{"user:update", "user:create"});
    }

    private void tryVerify(Subject subject, String username, String password, String role, String[] perms) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            System.out.println("是否认证通过: " + subject.isAuthenticated());
            // 验证角色
            subject.checkRole(role);
            // 鉴权
            for (String perm : perms) {
                System.out.println("验证权限: " + perm);
                subject.checkPermission(perm);
            }
            subject.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
