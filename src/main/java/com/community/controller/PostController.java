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

    @GetMapping("/page")
    public Result page(PostQueryParam postQueryParam){
        log.info("分页查询，参数: {}",postQueryParam);
        PageResult<Post> pageResult = postService.page(postQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{postId}")
    public Result getByPostId(@PathVariable Long postId){
        log.info("查询帖子，postId: {}",postId);
        Post post = postService.get(postId);
        return Result.success(post);
    }

    @GetMapping("/user")
    public Result getByUserId(PostQueryParam postQueryParam){
        log.info("查询帖子，userId: {}",postQueryParam);
        PageResult<Post> pageResult = postService.getByUser(postQueryParam);
        return Result.success(pageResult);
    }

    @PutMapping("/user")
    public Result update(@RequestBody Post post){
        log.info("更新帖子，post: {}",post);
        postService.update(post);
        return Result.success();
    }
    @DeleteMapping("/user/{postId}")
    public Result delete(@PathVariable Long postId){
        log.info("删除帖子，postId: {}",postId);
        postService.delete(postId);
        return Result.success();
    }



}
