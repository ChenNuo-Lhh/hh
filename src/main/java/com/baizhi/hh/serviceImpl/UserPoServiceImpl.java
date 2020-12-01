package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.dao.UserPoDao;
import com.baizhi.hh.po.NvPo;
import com.baizhi.hh.po.ScPo;
import com.baizhi.hh.po.UserPo;
import com.baizhi.hh.service.UserPoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserPoServiceImpl implements UserPoService {
    @Resource
    private UserPoDao userPoDao;

    /**
     * 前台:数据统计
     * 参数:
     * sex : 性别
     * num : 月份
     */
    @Override
    public List<UserPo> findAll(String sex, Integer num) {
        String value = String.valueOf(num);
        return userPoDao.findAll(sex, value);
    }

    /**
     * 前台:数据分布
     * 参数:
     * sex : 性别
     */
    @Override
    public ScPo findcity(String sex) {
        List<NvPo> city = userPoDao.findCity(sex);
        ScPo scPo = new ScPo();
        scPo.setSex(sex);
        scPo.setCity(city);
        return scPo;
    }
}
