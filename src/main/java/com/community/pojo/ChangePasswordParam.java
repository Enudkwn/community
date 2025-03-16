package com.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordParam {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
