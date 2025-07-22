package com.elementoj.module.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elementoj.module.test.domain.EleTest;

import java.util.List;

public interface EleTestMapper extends BaseMapper<EleTest> {

    Integer updateTest();

    Integer insertTest(List<EleTest> list);
}
