package com.baizhi.hh.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPo implements Serializable {
    private String id;
    private String createDate;
    private String sex;
    private String city;
}
