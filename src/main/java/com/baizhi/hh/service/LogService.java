package com.baizhi.hh.service;

import com.baizhi.hh.entity.Log;

import java.util.List;

public interface LogService {
    List<Log> findAll();

    void addLog(Log log);
}
