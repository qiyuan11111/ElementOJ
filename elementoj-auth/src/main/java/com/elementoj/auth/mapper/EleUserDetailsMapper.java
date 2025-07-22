package com.elementoj.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elementoj.auth.domain.EleUserDetails;
import org.apache.ibatis.annotations.Param;

public interface EleUserDetailsMapper extends BaseMapper<EleUserDetailsMapper> {
    EleUserDetails selectUserDetailsByUserName(@Param("user_name") String userName);
}
