package com.community.intercepter;

import com.community.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求路径
        String requestURI = request.getRequestURI();

        // 2.1.判断是不是登录请求，如果路径中包含/login，放行
        if (requestURI.contains("/login")){
            log.info("登录请求，放行");
            return true;
        }
        // 2.2.判断是不是注册请求，如果路径中包含/register，放行
        if (requestURI.contains("/register")){
            log.info("注册请求，放行");
            return true;
        }

        // 3.获取token
        String token = request.getHeader("token");

        // 4.判断tokne是否存在，如果不存在响应401
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }

        // 5.token存在，校验，校验失败响应401
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return false;
        }

        // 6.校验通过放行
        log.info("令牌合法，放行");
        return true;

    }
}
