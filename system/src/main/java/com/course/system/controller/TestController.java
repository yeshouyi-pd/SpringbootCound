package com.course.system.controller;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.service.TestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestServer testServer;

    @RequestMapping("/test")
    public List<Test> test() {
        TestExample example = new TestExample();
        example.createCriteria().andIdEqualTo("1");
        return testServer.getInfo(example);

    }
}
