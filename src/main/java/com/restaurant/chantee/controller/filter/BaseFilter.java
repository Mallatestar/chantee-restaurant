package com.restaurant.chantee.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Parent class for all custom filters, just for usability
 */
public abstract class BaseFilter implements Filter {

    static final Logger LOG = LogManager.getLogger(BaseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //NOP
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain);

    @Override
    public void destroy() {
        //NOP
    }
}
