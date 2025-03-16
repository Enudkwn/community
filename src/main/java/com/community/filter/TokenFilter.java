package com.community.filter;

import com.community.context.BaseContext;
import com.community.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1.获取请求路径
        String requestURI = request.getRequestURI();

        // 2.1.判断是不是登录请求，如果路径中包含/login，放行
        if (requestURI.contains("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        // 2.2.判断是不是注册请求，如果路径中包含/register，放行
        if (requestURI.contains("/register")){
            filterChain.doFilter(request, response);
            return;
        }

        // 3.获取token
        String token = request.getHeader("token");

        // 4.判断tokne是否存在，如果不存在响应401
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return;
        }

        // 5.token存在，校验，校验失败响应401
        try {
            Claims claims = JwtUtils.parseToken(token);
            Long userId = Long.valueOf(claims.get("id").toString());
            BaseContext.setCurrentId(userId);
            log.info("当前登录的用户Id: {}, 将其存入ThreadLocal", userId);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return;
        }

        // 6.校验通过放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);

        // 7.删除ThreadLocal中的数据
        BaseContext.removeCurrentId();

    }
}
