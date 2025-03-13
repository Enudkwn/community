package com.community.mapper;

import com.community.pojo.Post;
import com.community.pojo.PostQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface PostMapper {

    @Insert("insert into posts (title, create_user, content, create_time, update_time) values (#{title}, #{createUser}, #{content}, #{createTime}, #{updateTime})")
    void insert(Post post);


    List<Post> List(PostQueryParam postQueryParam);
}
