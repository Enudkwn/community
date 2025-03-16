package com.community.service;

import com.community.pojo.ChangePasswordParam;
import com.community.pojo.LoginInfo;
import com.community.pojo.User;

import java.util.Map;

public interface UserService {

    LoginInfo login(User user);

    Map<String, Long> register(User user);

    Object selectByUserId(Long userId);

    void updateIntro(User user);

    String updatePassword(ChangePasswordParam changePasswordParam);
}
