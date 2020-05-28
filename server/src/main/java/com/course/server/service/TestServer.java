package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServer {

    @Autowired
    private TestMapper testMapper;


    public List<Test> getInfo(TestExample example){
        return testMapper.selectByExample(example);

    }


}
