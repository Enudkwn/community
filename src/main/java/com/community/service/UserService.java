package com.community.service;

import com.community.pojo.LoginInfo;
import com.community.pojo.RegisterInfo;
import com.community.pojo.User;

import java.util.Map;

public interface UserService {

    LoginInfo login(User user);

    Map<String, Long> register(User user);
}
