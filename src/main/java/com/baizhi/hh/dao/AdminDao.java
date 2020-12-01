package com.baizhi.hh.dao;

import com.baizhi.hh.entity.Admin;


public interface AdminDao {
    Admin findName(String username);
}
