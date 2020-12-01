package com.baizhi.hh.dao;

import com.baizhi.hh.entity.Feedback;

import java.util.List;

public interface FeedbackDao {
    List<Feedback> findAll();
}
