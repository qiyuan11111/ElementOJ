package com.elementoj.module.news.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elementoj.module.news.domain.EleExpostulation;
import com.elementoj.module.news.mapper.EleExpostulationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class EleExpostulationService {
    @Resource
    EleExpostulationMapper eleExpostulationMapper;

    public EleExpostulation getExpostulationRandom() {
        Long count = getExpostulationCount();

        Random random = new Random();
        long id = (long) Math.floor(random.nextDouble() * count);

        return eleExpostulationMapper.selectOne(
                new QueryWrapper<EleExpostulation>()
                        .select("expostulation")
                        .last("limit " + id + ", 1")
        );
    }

    public Long getExpostulationCount(){
        return eleExpostulationMapper.selectCount(
                new QueryWrapper<EleExpostulation>()
                        .select("expostulation_id")
        );
    }
}
