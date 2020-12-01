package com.baizhi.hh.dao;

import com.baizhi.hh.entity.Video;
import com.baizhi.hh.po.VideoLikePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    List<Video> findAll(@Param("start") Integer start, @Param("rows") Integer rows);

    Video findId(String id);

    void addVideo(Video video);

    void delVideo(String id);

    void upVideo(Video video);

    long queryCounts();

    List<VideoLikePo> findLikeVideoName(@Param("content") String content);

}
