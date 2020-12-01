package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.dao.VideoPoDao;
import com.baizhi.hh.po.VideoPo;
import com.baizhi.hh.service.VideoPoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class VideoPoServiceImpl implements VideoPoService {

    @Resource
    private VideoPoDao videoPoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<VideoPo> findAll() {
        List<VideoPo> list = videoPoDao.findAll();
        list.forEach(a -> {
            a.setLikeCount(10);
        });
        return list;
    }
}
