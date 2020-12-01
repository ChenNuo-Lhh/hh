package com.baizhi.hh.app;

import com.baizhi.hh.common.CommonResult;
import com.baizhi.hh.entity.Category;
import com.baizhi.hh.po.VideoLikePo;
import com.baizhi.hh.po.VideoPo;
import com.baizhi.hh.service.CategoryService;
import com.baizhi.hh.service.VideoPoService;
import com.baizhi.hh.service.VideoService;
import com.baizhi.hh.util.AliyunUtil;
import com.baizhi.hh.util.ImageCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    private VideoPoService videoPoService;
    @Resource
    private VideoService videoService;
    @Resource
    private CategoryService categoryService;

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    /**
     * user
     * 用户调用验证码
     */
    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone) {
        String substring = ImageCodeUtil.getSecurityCode();
        log.debug("App_getPhoneCode_验证码为:{}", substring);
        String message = null;
        try {
            message = AliyunUtil.sendPhoneMsg(phone, substring);
            return new CommonResult().success(message, phone);
        } catch (Exception e) {
            return new CommonResult().failed(message);
        }
    }


    /**
     * 客户端时间排序查所有
     */
    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime() {
        List<VideoPo> poList = videoPoService.findAll();
        return new CommonResult().success(poList);
    }


    /**
     * 客户端查询类别
     * 一级类别
     */
    @RequestMapping("queryAllCategory")
    public CommonResult queryAllCategory() {
        List<Category> oneAll = categoryService.findOneAll(1, 0);
        return new CommonResult().success(oneAll);
    }

    /**
     * 客户端查询
     * 模糊查询
     **/
    @RequestMapping("queryByLikeVideoName")
    public CommonResult queryByLikeVideoName(String content) {

        List<VideoLikePo> likeVideoName = videoService.findLikeVideoName(content);

        if (likeVideoName.size() == 0 || likeVideoName == null) {
            return new CommonResult().failed("查无数据");
        } else {
            return new CommonResult().success(likeVideoName);
        }
    }

    /**
     * 客户端查询类别
     * 二级类别
     */
    @RequestMapping("queryCateVideoList")
    public CommonResult queryCateVideoList(String cateId) {
        List<Category> oneAll = categoryService.findTwoAll(1, 0, cateId);
        return new CommonResult().success(oneAll);
    }
}
