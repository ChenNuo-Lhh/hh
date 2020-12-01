package com.baizhi.hh.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.hh.entity.Category;
import com.baizhi.hh.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    @Resource
    private CategoryService categoryService;

    /**
     * 执行表格方法
     **/
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Category category, String oper) {
        log.debug("参数为:{}", category);
        log.debug("操作对象:{}", oper);
        log.debug("============");
        String str = null;
        if (StringUtils.equals("add", oper)) str = categoryService.addCate(category);
        if (StringUtils.equals("del", oper)) str = categoryService.delCate(category);
        if (StringUtils.equals("edit", oper)) {
            categoryService.updateCate(category);
            str = "修改成功";
        }
        return str;
    }

    /**
     * 查所有一级
     * page  当前页    rows  每页条数
     **/
    @ResponseBody
    @RequestMapping("queryOneAll")
    public HashMap<String, Object> queryOneAll(Integer page, Integer rows) {
        List<Category> list = categoryService.findOneAll(page, rows);
        long totalCounts = categoryService.queryCounts(1, null);
        log.debug("查所有总条数:{}", totalCounts);
        long total = totalCounts % rows == 0 ? totalCounts / rows : totalCounts / rows + 1;
        log.debug("查所有_共多少页:{}", total);
        HashMap<String, Object> result = new HashMap<>();
        result.put("page", page);
        result.put("records", totalCounts);
        result.put("total", total);
        result.put("rows", list);
        return result;
    }

    /**
     * 查所有二级
     * 字段详情见  queryAll
     **/
    @ResponseBody
    @RequestMapping("queryTwoAll")
    public HashMap<String, Object> queryTwoAll(Integer page, Integer rows, String id) {
        log.debug("查询二级类别的id为:{}", id);
        List<Category> list = categoryService.findTwoAll(page, rows, id);
        long totalCounts = categoryService.queryCounts(2, null);
        log.debug("查所有总条数:{}", totalCounts);
        long total = totalCounts % rows == 0 ? totalCounts / rows : totalCounts / rows + 1;
        log.debug("查所有_共多少页:{}", total);
        HashMap<String, Object> result = new HashMap<>();
        result.put("page", page);
        result.put("records", totalCounts);
        result.put("total", total);
        result.put("rows", list);
        return result;
    }
}
