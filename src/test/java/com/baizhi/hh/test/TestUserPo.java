package com.baizhi.hh.test;

import com.baizhi.hh.dao.UserPoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserPo {

    @Autowired
    private UserPoDao userPoDao;

    @Test
    public void findNameService() {
        userPoDao.findAll("女", "10").forEach(a -> System.out.println(a));
    }

    @Test
    public void findAll() {
        userPoDao.findAll("女", null).forEach(a -> System.out.println(a));
    }

    @Test
    public void findCity() {
        userPoDao.findCity("女").forEach(a -> System.out.println(a));
    }

}
