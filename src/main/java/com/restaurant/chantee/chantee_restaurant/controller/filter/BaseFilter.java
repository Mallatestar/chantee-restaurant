package com.restaurant.chantee.chantee_restaurant.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseFilter implements Filter {
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
