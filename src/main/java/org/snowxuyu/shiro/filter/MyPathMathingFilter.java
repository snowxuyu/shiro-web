package org.snowxuyu.shiro.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 * Create by ggx on 2016/5/11
 */
public class MyPathMathingFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("path matching");
        System.out.println(Arrays.toString((String[])mappedValue));
        return super.onPreHandle(request, response, mappedValue);
    }
}
