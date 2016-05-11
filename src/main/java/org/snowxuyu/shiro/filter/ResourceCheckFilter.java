package org.snowxuyu.shiro.filter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MSI on 2016/5/11.
 */
public class ResourceCheckFilter extends AccessControlFilter {

    @Getter
    @Setter
    private String errorUrl;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = this.getSubject(request, response);
        String url = this.getPathWithinApplication(request);
        System.out.println(url);
        return subject.isPermitted(url);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse hresponse = (HttpServletResponse) response;
        hresponse.sendRedirect(hrequest.getContextPath()+"/"+this.getErrorUrl());
        return false;
    }
}
