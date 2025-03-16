package com.community.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PostQueryParam {
    private Long userId;
    private Integer page = 1; // 当前页码，默认值为 1
    private Integer pageSize = 10; // 每页记录数，默认值为 10

    private String title; // 标题

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开始日期

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结束日期
}
