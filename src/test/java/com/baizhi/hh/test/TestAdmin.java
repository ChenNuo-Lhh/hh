package com.baizhi.hh.test;

import com.baizhi.hh.dao.AdminDao;
import com.baizhi.hh.entity.Admin;
import com.baizhi.hh.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAdmin {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    @Test
    public void findNameService() {
        Admin admin = adminService.findName("admin");
        System.out.println(admin);
    }

    @Test
    public void findName() {
        Admin admin = adminDao.findName("admin");
        System.out.println(admin);
    }
}
