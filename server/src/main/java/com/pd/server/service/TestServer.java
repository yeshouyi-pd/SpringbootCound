package com.pd.server.service;

import com.pd.server.domain.Test;
import com.pd.server.domain.TestExample;
import com.pd.server.mapper.TestMapper;
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
