package com.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long PostId;
    private String title;
    private Long createUser;
    private String content;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
