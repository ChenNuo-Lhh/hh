package com.baizhi.hh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback implements Serializable {
    private String id;
    private String title;
    private String content;
    private String userId;
}
