package com.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private Integer replyId;
    private Long postId;
    private Long createUser;
    private String createUsername;
    private String content;
    private Integer likeCount;
    private LocalDateTime createTime;
}
