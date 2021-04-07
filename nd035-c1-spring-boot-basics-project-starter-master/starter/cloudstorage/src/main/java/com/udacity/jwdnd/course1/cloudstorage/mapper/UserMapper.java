package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("Select * from USERS where username = #{username}")
    com.udacity.jwdnd.course1.cloudstorage.model.User getUser(String username);

    @Insert("Insert INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insert(User user);

}
