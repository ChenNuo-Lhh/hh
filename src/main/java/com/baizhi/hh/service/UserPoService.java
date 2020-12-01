package com.baizhi.hh.service;

import com.baizhi.hh.po.ScPo;
import com.baizhi.hh.po.UserPo;

import java.util.List;

public interface UserPoService {
    List<UserPo> findAll(String sex, Integer num);

    ScPo findcity(String sex);
}
