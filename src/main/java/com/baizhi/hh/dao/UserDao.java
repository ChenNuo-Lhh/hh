package com.baizhi.hh.dao;

import com.baizhi.hh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> findAll(@Param("page") Integer page, @Param("rows") Integer rows);

    void addUser(User user);

    void upStatus(User user);

    User findId(String id);

    long queryCounts();

}
