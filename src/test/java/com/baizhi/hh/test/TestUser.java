package com.baizhi.hh.test;

import com.baizhi.hh.dao.UserDao;
import com.baizhi.hh.entity.User;
import com.baizhi.hh.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestUser {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void findAll() {
        userService.findAll(1, 2).forEach(a -> System.out.println(a));
    }

    @Test
    public void addUser() {
        User user = new User("3563", "363", "36333453453", "34534", "345", 10.3, new Date(), 1);
        userDao.addUser(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId("34");
        user.setStatus(5);
        userService.upStatus(user);
    }

    @Test
    public void findId() {
        System.out.println(userService.findId("3563"));
    }

    @Test
    public void queryCounts() {
        System.out.println(userService.queryCounts());
    }
}
