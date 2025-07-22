package com.elementoj.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elementoj.api.system.domain.EleUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EleUserMapper extends BaseMapper<EleUser> {
    @Results(id = "eleUser",
    value = {
            @Result(property = "userId", column = "user_id", id = true),
            @Result(property = "userName", column = "user_name")
    })
    @ResultMap("eleUser")
    @Select({"select userId, userName from user where user_id = #{uid}"})
    EleUser getUserById(Long uid);
}
