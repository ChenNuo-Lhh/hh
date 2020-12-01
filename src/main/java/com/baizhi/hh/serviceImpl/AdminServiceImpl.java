package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.dao.AdminDao;
import com.baizhi.hh.entity.Admin;
import com.baizhi.hh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public Admin findName(String username) {
        return adminDao.findName(username);
    }
}
