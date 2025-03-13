package com.community.mapper;

import com.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select user_id, username from users where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(User user);

    @Select("select user_id, username from users where username = #{username}")
    User selectByUsername(String username);

    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("insert into users (username, password, create_time) values (#{username}, #{password}, #{createTime})")
    void insert(User user);
}
