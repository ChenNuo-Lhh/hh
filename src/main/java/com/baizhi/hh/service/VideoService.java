package com.baizhi.hh.service;

import com.baizhi.hh.entity.Video;
import com.baizhi.hh.po.VideoLikePo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    List<Video> findAll(Integer start, Integer rows);

    Video findId(String id);

    String addVideo(Video video);

    void delVideo(String id);

    long queryCounts();

    void upVideo(Video video);

    void upload(MultipartFile videoPath, String id);

    List<VideoLikePo> findLikeVideoName(String content);

}
