package com.baizhi.hh.test;

import com.baizhi.hh.service.VideoPoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestVideoPo {

    @Autowired
    private VideoPoService videoPoService;

    @Test
    public void findNameService() {
        videoPoService.findAll().forEach(a -> System.out.println(a));
    }

}
