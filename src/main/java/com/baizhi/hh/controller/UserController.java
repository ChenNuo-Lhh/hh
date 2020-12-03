package com.baizhi.hh.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.hh.entity.User;
import com.baizhi.hh.po.ScPo;
import com.baizhi.hh.poi.Poi;
import com.baizhi.hh.service.UserPoService;
import com.baizhi.hh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @Resource
    private Poi poi;

    @Resource
    private UserPoService userPoService;

    /**
     * 后台
     * 分页查所有
     */
    @ResponseBody
    @RequestMapping("userList")
    private HashMap<String, Object> userList(Integer page, Integer rows) {
        List<User> list = userService.findAll(page, rows);
        long totalCounts = userService.queryCounts();
        long total = totalCounts % rows == 0 ? totalCounts / rows : totalCounts / rows + 1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("page", page);
        hashMap.put("records", totalCounts);
        hashMap.put("total", total);
        hashMap.put("rows", list);
        return hashMap;
    }

    /**
     * 后台
     * 修改状态
     */
    @ResponseBody
    @RequestMapping("change")
    private String change(String id) {
        log.debug("接收到的id:{}", id);
        User u = userService.findId(id);
        User user = new User();
        if (u.getStatus() == 1) user.setStatus(2);
        if (u.getStatus() == 2) user.setStatus(1);
        user.setId(id);
        user.setScore(0.0);
        userService.upStatus(user);
        return "";
    }

    /**
     * 后台
     * 修改方法  String oper add:添加  edit:修改   del:删除
     */
    @ResponseBody
    @RequestMapping("edit")
    public String edit(User user, String oper) {
        log.info("当前操作: {}", oper);
        log.info("当前对象: {}", user);
        log.info("======分割线======");
        //判断是什么操作
        String uid = null;
        if (StringUtils.equals("add", oper)) {
            uid = userService.addUser(user);
        }
        if (StringUtils.equals("edit", oper)) {
            userService.upStatus(user);
        }
        return uid;
    }

    /**
     * 异步上传图片/视频
     */
    @RequestMapping("upload")
    public void upload(MultipartFile picImg, String uid) {
        userService.upload(picImg, uid);
    }

    /**
     * 后台
     * 发送手机验证码
     */
    @RequestMapping("sendOut")
    @ResponseBody
    public String sendOut(String phone) {
        log.debug("手机号为:{}", phone);
        String aliyun = userService.sendOut(phone);
        return aliyun;

    }

    /**
     * 后台
     * 导出用户信息
     */
    @RequestMapping("exportUser")
    public void exportUser() {
        File file = new File("F:\\testpoi.xls");
        poi.PoiExport(file, "学生信息表1");
    }

    /**
     * 后台
     * 导入用户信息
     */
    @RequestMapping("importUser")
    public void importUser() {
        File file = new File("F:\\testpoi.xls");
        poi.PoiImport(file, "学生信息表1");
    }

    /**
     * 后台
     * 数据统计
     * 思路:
     * 通过-性别和月份-查询每个月,不同性别的人数,
     * 因 : service返回为list类型,
     * 果 : 通过 list.size()  获取长度,即当前月,当前性别的人数
     * 通过for遍历,查询每个月的人数
     */
    @ResponseBody
    @RequestMapping("Statistics")
    public Object Statistics() {
        Map<Object, Object> map = new HashMap<>();
        List<Object> monthList = new ArrayList<>();
        List<Object> manList = new ArrayList<>();
        List<Object> wuManList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i + "月");
            int j = userPoService.findAll("男", i).size();
            manList.add(j);
            int m = userPoService.findAll("女", i).size();
            wuManList.add(m);
        }
        map.put("month", monthList);
        map.put("boy", manList);
        map.put("girl", wuManList);
        return map;
    }

    /**
     * 后台
     * 用户分布
     * 思路:值类型为__{性别:[地区:人数]}__{对象1,对象2-集合[对象1{地区:人数},对象2,...]}
     * 将值分别拆分封装,com.com.hh.po.ScPo 及 com.com.hh.po.NvPo
     */
    @ResponseBody
    @RequestMapping("distribution")
    public List<ScPo> distribution() {
        List<ScPo> list = null;
        ScPo boy = userPoService.findcity("男");
        ScPo gril = userPoService.findcity("女");
        list.add(boy);
        list.add(gril);
        return list;
    }
}
