package com.elementoj.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elementoj.auth.domain.EleUserDetails;
import org.apache.ibatis.annotations.*;

public interface EleUserDetailsMapper extends BaseMapper<EleUserDetailsMapper> {
    @ResultMap("EleUserDetailsMap")
    @Select({"select * " +
             "from (select user_name, password, user_id from user where user_name = #{user_name}) as a " +
             "  inner join (select authority from authority where user_name = #{user_name}) as b"})
    EleUserDetails selectUserDetailsByUserName(@Param("user_name") String userName);
}
