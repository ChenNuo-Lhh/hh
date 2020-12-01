package com.baizhi.hh.serviceImpl;

import com.baizhi.hh.annotcation.AddLog;
import com.baizhi.hh.annotcation.DelCahe;
import com.baizhi.hh.dao.VideoDao;
import com.baizhi.hh.entity.Video;
import com.baizhi.hh.po.VideoLikePo;
import com.baizhi.hh.service.VideoService;
import com.baizhi.hh.util.AliyunUploadUtil;
import com.baizhi.hh.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);
    @Autowired
    private VideoDao videoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Video> findAll(Integer start, Integer rows) {
        return videoDao.findAll((start - 1) * rows, rows);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Video findId(String id) {
        return videoDao.findId(id);
    }

    @Override
    @AddLog("Video-添加")
    @DelCahe
    public String addVideo(Video video) {
        String uuid = UUIDUtil.getUUID();
        video.setId(uuid);
        video.setUploadTime(new Date());
        video.setStatus(1);
        log.debug("添加的video信息:{}", video);

//        临时占位字段
        video.setCoverPath("3");
        video.setUserId("3");
        video.setGroupId("3");
        videoDao.addVideo(video);
        return uuid;
    }

    @Override
    @AddLog("Video-删除")
    @DelCahe
    public void delVideo(String id) {
        Video video = videoDao.findId(id);
        log.debug("删除查一个--video:{}", video);
//        删除视频/封面
        if (video.getCoverPath().equals("https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/cover/ZPWF.jpg") == false)
            AliyunUploadUtil.deleteFile(video.getCoverPath());
        AliyunUploadUtil.deleteFile(video.getVideoPath());
        videoDao.delVideo(id);
    }

    @Override
    @AddLog("Video-修改")
    @DelCahe
    public void upVideo(Video video) {
        videoDao.upVideo(video);
    }

    @Override
    @DelCahe
    public void upload(MultipartFile videoPath, String id) {
        log.debug("upload修改的id:{}", id);
        List list = AliyunUploadUtil.byteUpload(videoPath);
        log.debug("list集合下标0:{}", list.get(0));
        log.debug("list集合下标1:{}", list.get(1));
        Video video = videoDao.findId(id);
        video.setVideoPath(String.valueOf(list.get(0)));
        video.setCoverPath(String.valueOf(list.get(1)));
        videoDao.upVideo(video);
    }

    @Override
    public long queryCounts() {
        return videoDao.queryCounts();
    }

    public List<VideoLikePo> findLikeVideoName(String content) {
        List<VideoLikePo> likeVideoName = videoDao.findLikeVideoName(content);
        likeVideoName.forEach(a -> {
            a.setLikeCount(10);
        });
        return likeVideoName;
    }
}
