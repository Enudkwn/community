package com.community.service.impl;

import com.community.context.BaseContext;
import com.community.mapper.ReplyMapper;
import com.community.pojo.ReplyQueryParam;
import com.community.pojo.PageResult;
import com.community.pojo.Reply;
import com.community.service.ReplyService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    public void newReply(Reply reply) {
        reply.setCreateUser(BaseContext.getCurrentId());
        reply.setCreateTime(LocalDateTime.now());
        log.info("新建帖子：{}", reply);
        replyMapper.insert(reply);
        log.info("帖子ID：{}", reply.getPostId());
    }

    public PageResult<Reply> page(ReplyQueryParam replyQueryParam) {
        // 1.设置分页参数
        PageHelper.startPage(replyQueryParam.getPage(), replyQueryParam.getPageSize());

        // 2.执行查询
        List<Reply> replyList = replyMapper.List(replyQueryParam);

        // 3.封装PageResult对象并返回
        Page<Reply> p = (Page<Reply>) replyList;
        return new PageResult<Reply>(p.getTotal(), p.getResult());
    }
}
