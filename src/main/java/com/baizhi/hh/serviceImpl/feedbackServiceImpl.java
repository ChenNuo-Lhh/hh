package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.dao.FeedbackDao;
import com.baizhi.hh.entity.Feedback;
import com.baizhi.hh.service.FeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class feedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public List<Feedback> findAll() {
        return feedbackDao.findAll();
    }
}
