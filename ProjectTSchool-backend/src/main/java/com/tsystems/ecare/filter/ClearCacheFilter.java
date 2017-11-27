package com.tsystems.ecare.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ClearCacheFilter", urlPatterns = {"/*"})
public class ClearCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletResponse) response).setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        ((HttpServletResponse) response).setHeader("Pragma", "no-cache");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
