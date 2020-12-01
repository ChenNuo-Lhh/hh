package com.baizhi.hh.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScPo implements Serializable {
    private String sex;
    private List<NvPo> city;
}
