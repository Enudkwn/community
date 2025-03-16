package com.community.mapper;

import com.community.pojo.ReplyQueryParam;
import com.community.pojo.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    @Insert("insert into replies (post_id, create_user,create_username, content, create_time) values (#{postId}, #{createUser},#{createUsername}, #{content}, #{createTime})")
    void insert(Reply reply);

    List<Reply> List(ReplyQueryParam replyQueryParam);
}
