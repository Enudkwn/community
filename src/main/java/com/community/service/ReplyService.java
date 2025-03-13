package com.community.service;

import com.community.pojo.ReplyQueryParam;
import com.community.pojo.PageResult;
import com.community.pojo.Reply;

public interface ReplyService {
    void newReply(Reply reply);

    PageResult<Reply> page(ReplyQueryParam replyQueryParam);
}
