package org.snowxuyu.shiro.util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by snow on 2015/12/4.
 */
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static WebApplicationContext wc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //初始化spring的工厂
        wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    }

    public static WebApplicationContext getWc() {
        return wc;
    }

    public static Object getBean(String name) {
        return wc.getBean(name);
    }
}
