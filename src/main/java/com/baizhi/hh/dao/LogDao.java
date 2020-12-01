package com.baizhi.hh.dao;

import com.baizhi.hh.entity.Log;

import java.util.List;

public interface LogDao {
    List<Log> findAll();

    void addLog(Log log);
}
