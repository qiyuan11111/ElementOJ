package com.elementoj.datasource.incrementer;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

public class SmartIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return IdUtil.getSnowflakeNextId();
    }

    @Override
    public String nextUUID(Object entity) {
        return IdUtil.randomUUID();
    }
}
