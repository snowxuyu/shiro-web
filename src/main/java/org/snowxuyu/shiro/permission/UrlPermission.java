package org.snowxuyu.shiro.permission;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

/**
 * Created by MSI on 2016/5/11.
 */
public class UrlPermission implements Permission {

    @Getter
    @Setter
    private String url;

    public UrlPermission() {
    }

    public UrlPermission(String url) {
        this.url =url;
    }

    @Override
    public boolean implies(Permission permission) {
        if (!(permission instanceof  UrlPermission)) {
            return false;
        }
        UrlPermission up = (UrlPermission)permission;
        PatternMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.matches(this.getUrl(), up.getUrl());
    }
}
