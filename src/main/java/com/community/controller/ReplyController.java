package com.community.controller;

import com.community.pojo.ReplyQueryParam;
import com.community.pojo.PageResult;
import com.community.pojo.Reply;
import com.community.pojo.Result;
import com.community.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reply")
public class ReplyController {


    @Autowired
    private ReplyService replyService;

    @PostMapping
    public Result newReply(@RequestBody Reply reply)  {
        log.info("新建帖子：{}", reply);
        replyService.newReply(reply);
        return Result.success();
    }

    @GetMapping
    public Result page(ReplyQueryParam replyQueryParam){
        log.info("分页查询，参数: {}",replyQueryParam);
        if (replyQueryParam.getPostId() == null || replyQueryParam.getPostId().equals("")){
            return Result.error("帖子ID不能为空");
        }
        PageResult<Reply> pageResult = replyService.page(replyQueryParam);
        return Result.success(pageResult);
    }
    
}
