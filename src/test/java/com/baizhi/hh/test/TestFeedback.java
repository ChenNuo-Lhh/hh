package com.baizhi.hh.test;

import com.baizhi.hh.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestFeedback {

    @Autowired
    private FeedbackService feedbackService;


    @Test
    public void findName() {
        feedbackService.findAll().forEach(a -> System.out.println(a));
    }
}
