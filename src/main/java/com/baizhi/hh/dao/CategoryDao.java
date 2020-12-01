package com.baizhi.hh.dao;

import com.baizhi.hh.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    //    查所有
    List<Category> findAll();

    //    查询长度
    Long queryCounts(@Param("levels") Integer levels, @Param("parentId") String parentId);

    //查询一级类别
    List<Category> findOneAll(@Param("start") Integer start, @Param("rows") Integer rows);

    //查询二级类别
    List<Category> findTwoAll(@Param("start") Integer start, @Param("rows") Integer rows, @Param("parentId") String parentId);

    //添加
    void addCate(Category category);

    //删除
    void delCate(String id);

    //查询所有的字类
    List<Category> findId(String id);

    //修改
    void updateCate(Category category);
}
