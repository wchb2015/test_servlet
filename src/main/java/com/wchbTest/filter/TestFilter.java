package com.wchbTest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "TestFilter")
public class TestFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(TestFilter.class);

    public void destroy() {
        LOG.info("destroy!!!");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LOG.info("doFilter!!!, request url :{}", ((HttpServletRequest) req).getRequestURL().toString());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        LOG.info("TestFilter init!!!");
    }

}
