package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.annotcation.AddLog;
import com.baizhi.hh.annotcation.DelCahe;
import com.baizhi.hh.dao.UserDao;
import com.baizhi.hh.entity.User;
import com.baizhi.hh.service.UserService;
import com.baizhi.hh.util.AliyunUploadUtil;
import com.baizhi.hh.util.AliyunUtil;
import com.baizhi.hh.util.ImageCodeUtil;
import com.baizhi.hh.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll(Integer page, Integer rows) {
        return userDao.findAll((page - 1) * rows, rows);
    }

    /**
     * 图片上传阿里云
     * 参数 :
     * picImg : 图片文件
     * uid : 添加方法返回的UUID 通过该id进行数据的修改
     */
    @Override
    @DelCahe
    public void upload(MultipartFile picImg, String uid) {
        log.debug("upload修改的id:{}", uid);
        List list = AliyunUploadUtil.byteUpload(picImg);
        log.debug("list集合下标0:{}", list.get(0));
        User user = userDao.findId(uid);
        user.setPicImg(String.valueOf(list.get(0)));
        userDao.upStatus(user);
    }

    /**
     * 验证码
     * 参数 :
     * phone : 接收验证码的目标手机号
     */
    @Override
    public String sendOut(String phone) {
        String securityCode = ImageCodeUtil.getSecurityCode();
        log.debug("验证码为:{}", securityCode);
        String aliyun = AliyunUtil.sendPhoneMsg(phone, securityCode);
        return aliyun;

    }

    /**
     * 将id返回至页面,再通过id查询
     * 获取库内当前id的详细信息
     * 再进行修改值
     * 作用 : 解决存入数据库的字段和阿里云的网路路径不相同问题
     */
    @Override
    @AddLog("User-添加")
    @DelCahe
    public String addUser(User user) {
        //        数据入库
        String uuid = UUIDUtil.getUUID();
        user.setId(uuid);
        user.setStatus(1);
        user.setScore(0.0);
        user.setCreateDate(new Date());
        userDao.addUser(user);
        return uuid;
    }

    @Override
    @AddLog("User-修改")
    @DelCahe
    public void upStatus(User user) {
        userDao.upStatus(user);
    }

    @Override
    public long queryCounts() {
        return userDao.queryCounts();
    }

    @Override
    public User findId(String id) {
        return userDao.findId(id);
    }
}
