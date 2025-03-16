package com.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId; //ID
    private String username; //用户名
    private String password; //密码
    private String intro;//介绍
    private LocalDateTime createTime; //创建时间
}
