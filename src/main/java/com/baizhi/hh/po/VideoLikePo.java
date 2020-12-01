package com.baizhi.hh.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoLikePo implements Serializable {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Data uploadTime;
    private String description;
    private Integer likeCount;
    private String cateName;
    private String categoryId;
    private String userId;
    private String userName;
}
