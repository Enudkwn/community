package com.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyQueryParam {
    private Integer page = 1; // 当前页码，默认值为 1
    private Integer pageSize = 10; // 每页记录数，默认值为 10

    private String postId;
}
