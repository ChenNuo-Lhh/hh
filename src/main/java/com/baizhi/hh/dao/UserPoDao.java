package com.baizhi.hh.dao;

import com.baizhi.hh.po.NvPo;
import com.baizhi.hh.po.UserPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPoDao {
    List<UserPo> findAll(@Param("sex") String sex, @Param("num") String num);

    List<NvPo> findCity(String sex);
}
