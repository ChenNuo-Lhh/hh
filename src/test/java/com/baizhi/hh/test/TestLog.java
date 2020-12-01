package com.baizhi.hh.test;

import com.baizhi.hh.entity.Log;
import com.baizhi.hh.service.LogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestLog {

    @Autowired
    private LogService logService;


    @Test
    public void findName() {
        Log loga = new Log("absga", "saga", new Date(), "adsga", "aeg");
        logService.addLog(loga);
//            logService.findAll().forEach(a-> System.out.println(a));
    }
}
