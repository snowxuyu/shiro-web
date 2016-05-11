package org.snowxuyu.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by MSI on 2016/5/11.
 */
public class UrlPermissionResovler implements PermissionResolver {
    @Override
    public Permission resolvePermission(String s) {
        if (s.startsWith("/")) {
            return new UrlPermission(s);
        } else {
            return new WildcardPermission(s);
        }
    }
}
