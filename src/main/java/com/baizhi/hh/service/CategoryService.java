package com.baizhi.hh.service;

import com.baizhi.hh.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    List<Category> findAll();

    Long queryCounts(Integer levels, String parentId);

    List<Category> findOneAll(Integer start, Integer rows);

    List<Category> findTwoAll(Integer start, Integer rows, String parentId);

    String addCate(Category category);

    String delCate(Category category);

    List<Category> findId(String id);

    void updateCate(Category category);
}
