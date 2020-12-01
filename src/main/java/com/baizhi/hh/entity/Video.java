package com.baizhi.hh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {
    private String id;
    private String title; //标题
    private String brief; //简介
    private String coverPath; //封面
    private String videoPath; //视频
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime; //上传时间
    private String categoryId; //类别id
    private String userId; //用户id
    private String groupId; //分组id
    private Integer status; //状态

}
