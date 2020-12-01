package com.baizhi.hh.test;

import com.baizhi.hh.dao.VideoDao;
import com.baizhi.hh.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestVideo {
    @Autowired
    private VideoDao videoDao;

    @Test
    public void findAll() {
        videoDao.findAll(0, 1).forEach(a -> System.out.println(a));
    }

    @Test
    public void queryCounts() {
        System.out.println(videoDao.queryCounts());
    }

    @Test
    public void findId() {
        System.out.println(videoDao.findId("2635"));
    }

    @Test
    public void add() {
        Video video = new Video("2634", "标题", "简介", "封面路径", "视频路径", new Date(), "119f5", "365", "36", 1);
        videoDao.addVideo(video);
    }

    @Test
    public void del() {
        videoDao.delVideo("2635");
    }

    @Test
    public void LikeVideoName() {
        videoDao.findLikeVideoName("g").forEach(a -> System.out.println(a));
    }

    @Test
    public void update() {
        Video video = new Video("2634", "标题1", "简介", "封面路径", "视频路径", new Date(), "119f5", "365", "36", 1);
        videoDao.upVideo(video);
    }
}
