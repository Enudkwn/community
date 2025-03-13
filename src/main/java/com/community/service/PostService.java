package com.community.service;

import com.community.pojo.PageResult;
import com.community.pojo.Post;
import com.community.pojo.PostQueryParam;

public interface PostService {
    void newPost(Post post);

    PageResult<Post> page(PostQueryParam postQueryParam);
}
