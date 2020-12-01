package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.dao.LogDao;
import com.baizhi.hh.entity.Log;
import com.baizhi.hh.service.LogService;
import com.baizhi.hh.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class LogServiceImpl implements LogService {
    @Resource
    private LogDao logDao;

    @Override
    public List<Log> findAll() {
        return logDao.findAll();
    }

    @Override
    public void addLog(Log log) {
        log.setId(UUIDUtil.getUUID());
        logDao.addLog(log);
    }
}
