package com.hand.crm.springboot.test.demo.util;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
@WebFilter(urlPatterns = "/api/*",filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter loginFilter");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String username = request.getParameter("username");
        if(username.equals("springboot")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            System.out.println("过滤不通过");
        }
    }

    @Override
    public void destroy() {

    }
}
