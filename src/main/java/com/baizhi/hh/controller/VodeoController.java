package com.baizhi.hh.controller;


import com.alibaba.druid.util.StringUtils;
import com.baizhi.hh.entity.Video;
import com.baizhi.hh.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("video")
public class VodeoController {

    private static final Logger log = LoggerFactory.getLogger(VodeoController.class);
    @Autowired
    private VideoService videoService;

    /**
     * 后台
     * 查所有
     */
    @ResponseBody
    @RequestMapping("findAll")
    public HashMap<String, Object> findAll(Integer page, Integer rows) {
        List<Video> list = videoService.findAll(page, rows);
        long totalCounts = videoService.queryCounts();
        long total = totalCounts % rows == 0 ? totalCounts / rows : totalCounts / rows + 1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("records", totalCounts);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    /**
     * 执行表格方法
     **/
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Video video, String oper) {
        log.debug("参数为:{}", video);
        log.debug("操作对象:{}", oper);
        log.debug("======分割线======");
        String uid = null;
        if (StringUtils.equals("add", oper)) uid = videoService.addVideo(video);
        if (StringUtils.equals("edit", oper)) videoService.upVideo(video);
        if (StringUtils.equals("del", oper)) videoService.delVideo(video.getId());
        return uid;
    }

    /**
     * 后台
     * 修改状态
     */
    @ResponseBody
    @RequestMapping("change")
    private String change(String id) {
        log.debug("video接收到的id:{}", id);
        Video videoServiceId = videoService.findId(id);

        Video video = new Video();
        video.setId(videoServiceId.getId());
//        修改状态
        if (videoServiceId.getStatus() == 1) video.setStatus(2);
        if (videoServiceId.getStatus() == 2) video.setStatus(1);
//        修改视频路径
        video.setCoverPath("https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/cover/ZPWF.jpg");
        log.debug("video值:{}", video);
        videoService.upVideo(video);
        return "";
    }

    /**
     * 异步上传图片/视频
     */
    @RequestMapping("upload")
    public void upload(MultipartFile videoPath, String uid) {
        videoService.upload(videoPath, uid);
    }


}
