package com.community.service.impl;

import com.community.context.BaseContext;
import com.community.mapper.PostMapper;
import com.community.mapper.UserMapper;
import com.community.pojo.PageResult;
import com.community.pojo.Post;
import com.community.pojo.PostQueryParam;
import com.community.service.PostService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;

    public void newPost(Post post) {
        post.setCreateUser(BaseContext.getCurrentId());
        post.setCreateUsername(userMapper.selectUsernameByUserId(post.getCreateUser()).getUsername());
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        log.info("新建帖子：{}", post);
        postMapper.insert(post);
        log.info("帖子ID：{}", post.getPostId());
    }

    public PageResult<Post> page(PostQueryParam postQueryParam) {
        // 1.设置分页参数
        PageHelper.startPage(postQueryParam.getPage(), postQueryParam.getPageSize());

        // 2.执行查询
        List<Post> postList = postMapper.List(postQueryParam);

        // 3.封装PageResult对象并返回
        Page<Post> p = (Page<Post>) postList;
        return new PageResult<Post>(p.getTotal(), p.getResult());
    }

    public Post get(Long postId) {
        Post post = postMapper.selectByPrimaryKey(postId);
        return post;
    }

    public PageResult<Post> getByUser(PostQueryParam postQueryParam) {
        // 1.设置分页参数
        PageHelper.startPage(postQueryParam.getPage(), postQueryParam.getPageSize());

        // 2.执行查询
        List<Post> postList = postMapper.List(postQueryParam);

        // 3.封装PageResult对象并返回
        Page<Post> p = (Page<Post>) postList;
        return new PageResult<Post>(p.getTotal(), p.getResult());
    }

    public void update(Post post) {
        post.setUpdateTime(LocalDateTime.now());
        postMapper.updateByUserId(post);
    }

    public void delete(Long postId) {
        postMapper.delete(postId);
    }
}
