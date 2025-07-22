package com.elementoj.module.test.service;

import com.elementoj.module.test.domain.EleTest;
import com.elementoj.module.test.mapper.EleTestMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EleTestService {
    @Resource
    private EleTestMapper eleTestMapper;

    @Async
    public void test(){
        eleTestMapper.updateTest();
    }

    public void test2(List<EleTest> list){
        eleTestMapper.insertTest(list);
    }
}
