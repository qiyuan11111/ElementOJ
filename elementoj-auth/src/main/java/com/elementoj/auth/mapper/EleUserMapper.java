package com.elementoj.auth.mapper;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.elementoj.auth.domain.EleAuthority;
import com.elementoj.auth.domain.EleUser;
import com.elementoj.auth.domain.dto.EleUserDTO;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.val;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Optional;

public interface EleUserMapper extends MPJBaseMapper<EleUser> {
    default EleUser getUserByUserId(Long uid, SFunction<EleUser, Serializable>... fields) {
        val select = new LambdaQueryWrapper<EleUser>()
                .eq(EleUser::getUserId, uid);

        Optional.ofNullable(fields)
                .filter(ArrayUtil::isNotEmpty)
                .ifPresent(fs -> select.select(fields));

        return selectOne(select);
    }

    default EleUser getUserNameAndIdByUserId(Long uid) {
        return getUserByUserId(uid, EleUser::getUserId, EleUser::getUsername);
    }

    default Long getUserCountByUserName(String userName) {
        return selectCount(
                new LambdaQueryWrapper<EleUser>()
                        .eq(EleUser::getUsername, userName)
        );
    }

    default int registerUser(EleUser user) {
        user.setUserId("U-" + IdUtil.fastUUID());
        return insert(user);
    }


    //    @Select({"""
//            select *
//                from (select user_name, password, user_id from user where user_name = #{user_name}) as a
//                inner join (select authority from authority where user_name = #{user_name}) as b
//            """})
//    EleUser selectUserDetailsByUserName(@Param("user_name") String userName);
    default EleUserDTO selectUserDetailsByUserName(@Param("user_name") String userName) {
        return selectJoinOne(EleUserDTO.class, new MPJLambdaWrapper<>(EleUser.class)
                .select(EleUser::getUsername, EleUser::getPassword, EleUser::getUserId)
                .selectCollection(EleUserDTO::getAuthority, t -> t
                        .result(EleAuthority::getAuthority)
                )
                .leftJoin(EleAuthority.class, on -> on
                        .eq(EleAuthority::getUsername, userName)
                        .eq(EleAuthority::getUserId, EleUser::getUserId)
                )
        );
    }

//    default EleUser getUserByUserId(Long userId){
//        return eleUserMapper.selectOne(
//                new QueryWrapper<EleUser>()
//                        .select("user_id", "user_name", "nick_name", "email")
//                        .eq("user_id", userId)
//        );
//    }
}
