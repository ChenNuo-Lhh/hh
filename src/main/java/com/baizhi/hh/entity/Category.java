package com.baizhi.hh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private String id;
    private String cateName;
    private Integer levels;
    private String parentId;

    private List<Category> cate;

}
