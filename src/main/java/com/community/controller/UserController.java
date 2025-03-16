package com.community.controller;

import com.community.pojo.Result;
import com.community.pojo.User;
import com.community.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public Result selectByUserId (@PathVariable Long userId) {
        log.info("查询用户: " + userId);
        return Result.success(userService.selectByUserId(userId));
    }

    @PutMapping("/intro")
    public Result updateIntro(@RequestBody User user) {
        log.info("修改个人简介: " + user);
        userService.updateIntro(user);
        return Result.success();
    }

    @PutMapping("/password")
    public Result updatePassword(@RequestBody User user) {
        log.info("修改密码: " + user);
        userService.updatePassword(user);
        return Result.success();
    }
}
