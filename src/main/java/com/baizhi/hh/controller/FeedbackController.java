package com.baizhi.hh.controller;

import com.baizhi.hh.entity.Feedback;
import com.baizhi.hh.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @ResponseBody
    @RequestMapping("findAll")
    public List<Feedback> findAll() {
        return feedbackService.findAll();
    }

}
