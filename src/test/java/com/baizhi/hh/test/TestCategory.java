package com.baizhi.hh.test;

import com.baizhi.hh.dao.CategoryDao;
import com.baizhi.hh.entity.Category;
import com.baizhi.hh.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCategory {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void del() {
        categoryDao.delCate("1212");
    }

    @Test
    public void update() {
        Category category = new Category();
        category.setId("12312");
        category.setCateName("isgo");
        categoryDao.updateCate(category);
    }


    @Test
    public void add() {
        Category category = new Category("121", "22222", 1, null, null);
        categoryDao.addCate(category);
    }

    @Test
    public void findID() {
        categoryDao.findId("456345").forEach(a -> System.out.println(a));
    }

    @Test
    public void queryCounts() {
        Long aLong = categoryDao.queryCounts(1, null);
        System.out.println(aLong);
    }

    @Test
    public void findAll() {
        categoryService.findAll().forEach(a -> System.out.println(a));
    }

    @Test
    public void findOneAll() {
        categoryService.findOneAll(1, 0).forEach(a -> System.out.println(a));
    }

    @Test
    public void findTwoAll() {
        categoryService.findTwoAll(0, 0, "26585").forEach(a -> System.out.println(a));
    }
}
