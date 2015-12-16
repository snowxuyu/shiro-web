package org.snowxuyu.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.framework.basic.system.ResponseEntity;
import org.snowxuyu.shiro.entity.Resources;
import org.snowxuyu.shiro.entity.User;
import org.snowxuyu.shiro.service.UserService;
import org.snowxuyu.shiro.util.InitServlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by snow on 2015/12/4.
 */
public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("==========进入授权操作========");
        User user = (User)principal.getPrimaryPrincipal();
        String uid = user.getId();
        System.out.println(user.getId()+","+user.getNickName());
        UserService userService = (UserService)InitServlet.getBean("userService");
        ResponseEntity resp1 = userService.listRoleSnByUser(uid);
        ResponseEntity resp2 = userService.listAllResource(uid);
        List<String> roles = (List<String>)resp1.getData();
        List<Resources> reses = (List<Resources>)resp2.getData();
        List<String> permissions = new ArrayList<String>();
        for(Resources r : reses) {
            permissions.add(r.getUrl());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<String>(roles));
        info.setStringPermissions(new HashSet<String>(permissions));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserService userService = (UserService) InitServlet.getBean("userService");
        String username = token.getPrincipal().toString();
        String password = new String((char[]) token.getCredentials());
        ResponseEntity resp = userService.login(username, password);
        User user = (User) resp.getData();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassWord(), this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getUserName()));
        return info;
    }
}
