package com.baizhi.hh.service;

import com.baizhi.hh.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> findAll(Integer page, Integer rows);

    String addUser(User user);

    void upStatus(User user);

    User findId(String id);

    String sendOut(String phone);

    long queryCounts();

    void upload(MultipartFile picImg, String id);

}
