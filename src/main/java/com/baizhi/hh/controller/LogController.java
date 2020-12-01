package com.baizhi.hh.controller;

import com.baizhi.hh.entity.Log;
import com.baizhi.hh.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("Log")
public class LogController {

    @Resource
    private LogService logService;

    @ResponseBody
    @RequestMapping("findAll")
    public List<Log> findAll() {
        return logService.findAll();
    }

}
