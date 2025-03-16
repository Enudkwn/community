package com.community.service.impl;

import com.community.mapper.UserMapper;
import com.community.pojo.LoginInfo;
import com.community.pojo.RegisterInfo;
import com.community.pojo.User;
import com.google.common.hash.Hashing;
import com.community.service.UserService;
import com.community.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public LoginInfo login(User user) {
        user.setPassword(
                Hashing.sha256()
                        .hashString(user.getPassword(), StandardCharsets.UTF_8)
                        .toString()
        );
        User u = userMapper.selectByUsernameAndPassword(user);

        if(u != null){
            log.info("登录成功，用户信息：{}", u);
            // 生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getUserId());
            claims.put("username", u.getUsername());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(u.getUserId(), u.getUsername(), jwt);
        }

        return null;
    }

    public Map<String, Long> register(User user) {
        // 检查是否已存在该用户
        User u = userMapper.selectByUsername(user.getUsername());
        if(u == null){
            // sha256加密
            user.setPassword(
                Hashing.sha256()
                        .hashString(user.getPassword(), StandardCharsets.UTF_8)
                        .toString()
            );
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);
            Map<String, Long> claims = new HashMap<>();
            claims.put("user_id", user.getUserId());
            return claims;
        }
        log.info("注册失败，用户已存在：{}", user);
        return null;
    }

    public Object selectByUserId(Long userId) {
        return userMapper.selectByUserId(userId);
    }

    public void updateIntro(User user) {
        userMapper.updateIntro(user);
    }

    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }
}
