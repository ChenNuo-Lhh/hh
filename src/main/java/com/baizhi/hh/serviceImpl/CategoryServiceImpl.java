package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.annotcation.DelCahe;
import com.baizhi.hh.dao.CategoryDao;
import com.baizhi.hh.entity.Category;
import com.baizhi.hh.service.CategoryService;
import com.baizhi.hh.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private CategoryService categoryService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Long queryCounts(Integer levels, String parentId) {
        return categoryDao.queryCounts(levels, parentId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findOneAll(Integer start, Integer rows) {
        List<Category> list = categoryDao.findOneAll((start - 1) * rows, rows);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findTwoAll(Integer start, Integer rows, String parentId) {
        return categoryDao.findTwoAll(start, rows, parentId);
    }

    @Override
    @DelCahe
    public String addCate(Category category) {
        List<Category> list = categoryService.findAll();
        int i = 0;
        String str = null;
//        判断集合中是否有相同的名称
        for (Category cate : list) {
            if (cate.getCateName().equals(category.getCateName())) {
                i = 1;
            }
        }
        if (i != 1) {
            String id = UUIDUtil.getUUID().substring(2, 7);
            log.debug("当前添加操作id为:{}", id);
            category.setId(id);
            categoryDao.addCate(category);
            str = "添加成功";
        } else {
            str = "已有相同名称,请更换名称后重试";
        }
        return str;
    }

    /**
     * 因为表为自连接
     * 所以当父类id的长度为0是表示没有子类别
     */
    @Override
    @DelCahe
    public String delCate(Category category) {
        int i = 1;
        String str = null;
//        长度==0 删除父类
        if (categoryDao.findId(category.getId()).size() == 0) {
            i = 2;
            categoryDao.delCate(category.getId());
        } else {
            i = 0;
        }
//        父类id不为空 删除子类
        if (category.getParentId() != null) {
            i = 2;
            categoryDao.delCate(category.getId());
        }
//判断删除状态
        if (i == 1) str = "删除失败";
        if (i == 2) str = "删除成功";
        if (i == 0) str = "存在子类别,请检查后重试";
        return str;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findId(String id) {
        List<Category> list = categoryDao.findId(id);
        return list;
    }

    @Override
    @DelCahe
    public void updateCate(Category category) {
        categoryDao.updateCate(category);
    }
}
