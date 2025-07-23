package com.elementoj.auth.mapper;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.elementoj.api.system.domain.EleUser;
import lombok.val;

import java.io.Serializable;
import java.util.Optional;

public interface EleUserMapper extends BaseMapper<EleUser> {
//    @ResultMap("EleUserMap")
    default EleUser getUserByUserId(Long uid, SFunction<EleUser, Serializable>... fields){
        val select = new LambdaQueryWrapper<EleUser>()
                .eq(EleUser::getUserId, uid);

        Optional.ofNullable(fields)
                .filter(ArrayUtil::isNotEmpty)
                .ifPresent(fs -> select.select(fields));

        return selectOne(select);
    }

    default EleUser getUserNameAndIdByUserId(Long uid){
        return getUserByUserId(uid, EleUser::getUserId, EleUser::getUserName);
    }

    default Long getUserCountByUserName(String userName) {
        return selectCount(
                new LambdaQueryWrapper<EleUser>()
                        .eq(EleUser::getUserName, userName)
        );
    }

    default int registerUser(EleUser user) {
        return insert(user);
    }

//    default EleUser getUserByUserId(Long userId){
//        return eleUserMapper.selectOne(
//                new QueryWrapper<EleUser>()
//                        .select("user_id", "user_name", "nick_name", "email")
//                        .eq("user_id", userId)
//        );
//    }
}
