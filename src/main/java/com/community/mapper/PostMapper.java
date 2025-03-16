package com.community.mapper;

import com.community.pojo.Post;
import com.community.pojo.PostQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    @Insert("insert into posts (title, create_user, create_username, content, create_time, update_time) values (#{title}, #{createUser}, #{createUsername}, #{content}, #{createTime}, #{updateTime})")
    void insert(Post post);


    List<Post> List(PostQueryParam postQueryParam);

    @Select("select * from posts where post_id = #{postId}")
    Post selectByPrimaryKey(Long id);

    @Select("select * from posts where create_user = #{userId}")
    Post selectByUserId(Long userId);

    @Select("update posts set title = #{title}, content = #{content}, update_time = #{updateTime} where create_user = #{createUser}")
    void updateByUserId(Post post);

    @Delete("delete from posts where post_id = #{postId}")
    void delete(Long postId);
}
