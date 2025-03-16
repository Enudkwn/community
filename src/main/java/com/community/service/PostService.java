package com.community.service;

import com.community.pojo.PageResult;
import com.community.pojo.Post;
import com.community.pojo.PostQueryParam;

public interface PostService {
    void newPost(Post post);

    PageResult<Post> page(PostQueryParam postQueryParam);

    Post get(Long postId);

    PageResult<Post> getByUser(PostQueryParam postQueryParam);

    void update(Post postQueryParam);

    void delete(Long postId);
}
