package com.game.webservice.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //查询用户名是否重复
    User findInfo(@Param("name") String name);
    List<User> getAll();
    //验证用户
    User CheckAdmin(@Param("name") String name, @Param("pwd") String pwd);
    //添加用户
    void addAdmin(@Param("usrname") String usrname,
                  @Param("password") String password,
                  @Param("province") String province,
                  @Param("job") String job,
                  @Param("phone") String phone,
                  @Param("email") String email,
                  @Param("sum") String sum);
    List<User> getUsers(@Param("start") int start,@Param("length") int length);
    List<User> findUsers(@Param("name") String name);
}
