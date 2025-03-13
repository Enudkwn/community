package com.community.controller;

import com.community.pojo.PageResult;
import com.community.pojo.Post;
import com.community.pojo.PostQueryParam;
import com.community.pojo.Result;
import com.community.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Result newPost(@RequestBody Post post)  {
        log.info("新建帖子：{}", post);
        postService.newPost(post);
        return Result.success();
    }

    @GetMapping
    public Result page(PostQueryParam postQueryParam){
        log.info("分页查询，参数: {}",postQueryParam);
        PageResult<Post> pageResult = postService.page(postQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        log.info("删除帖子，id: {}",id);
        postService.delete(id);
        return Result.success();
    }


}
