package com.community.mapper;

import com.community.pojo.ChangePasswordParam;
import com.community.pojo.User;
import org.apache.ibatis.annotations.*;
import org.checkerframework.checker.units.qual.C;

@Mapper
public interface UserMapper {


    @Select("select user_id, username from users where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(User user);

    @Select("select user_id, username from users where username = #{username}")
    User selectByUsername(String username);

    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("insert into users (username, password, create_time) values (#{username}, #{password}, #{createTime})")
    void insert(User user);

    @Select("select user_id, username from users where user_id = #{userId}")
    User selectUsernameByUserId(Long userId);

    @Select("select * from users where user_id = #{userId}")
    User selectByUserId(Long userId);

    @Update("update users set intro = #{intro} where user_id = #{userId}")
    void updateIntro(User user);

    @Update("update users set password = #{newPassword} where user_id = #{userId}")
    void updatePassword(ChangePasswordParam changePasswordParam);
}
