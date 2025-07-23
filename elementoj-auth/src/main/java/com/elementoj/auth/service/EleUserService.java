package com.elementoj.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elementoj.api.system.domain.EleUser;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.auth.domain.EleUserDetails;
import com.elementoj.auth.mapper.EleUserMapper;
import com.elementoj.common.core.web.constant.UserConstants;
import com.elementoj.common.security.utils.SecurityUtils;
import com.elementoj.common.core.web.utils.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

@Service
public class EleUserService {

    @Resource
    private EleUserMapper eleUserMapper;

    public void register(EleUser user) {
        Stream.<Supplier<EleUserException>>of(
                        () -> StringUtils.isBlank(user.getUserName()) ?
                                new EleUserException("用户名不能为空", ELE_USER_NONE_USERNAME) : null,

                        () -> StringUtils.isBlank(user.getPassword()) ?
                                new EleUserException("密码不能为空", ELE_USER_NONE_PASSWORD) : null,

                        () -> {
                            int pwdLen = user.getPassword().length();
                            return (pwdLen < UserConstants.MinPasswordLength || pwdLen > UserConstants.MaxPasswordLength) ?
                                    new EleUserException("密码长度必须在" + UserConstants.MinPasswordLength + "到" +
                                            UserConstants.MaxPasswordLength + "之间", ELE_USER_IRREGULAR_PASSWORD) : null;
                        },

                        () -> {
                            int nameLen = user.getUserName().length();
                            return (nameLen < UserConstants.MinUserNameLength || nameLen > UserConstants.MaxUserNameLength) ?
                                    new EleUserException("用户名长度必须在" + UserConstants.MinUserNameLength + "到" +
                                            UserConstants.MaxUserNameLength + "之间", ELE_USER_IRREGULAR_USERNAME) : null;
                        }
                )
                .map(Supplier::get)
                .filter(Objects::nonNull) // 过滤出非空的异常对象
                .findFirst()              // 获取第一个满足条件的异常
                .ifPresent(ex -> {
                    throw ex;
                }); // 如果存在则抛出

        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        Long count = eleUserMapper.getUserCountByUserName(user.getUserName());
        if (count > 0)
            throw new EleUserException("用户已存在", ELE_USER_MULTIPLE_USERS);

        int result = eleUserMapper.registerUser(user);
        if (result == 0)
            throw new EleUserException("注册失败", ELE_USER_REGISTER_FAILED);
    }

    public EleUser getUserByToken(Authentication authentication) {
        return Optional.ofNullable(authentication)
                .map(auth -> (EleUserDetails) auth.getPrincipal())
                .map(EleUserDetails::getUserId)
                .map(userId -> eleUserMapper.getUserNameAndIdByUserId(userId))
                .orElseThrow(() ->
                        authentication == null ?
                                new EleUserException("authentication为空", ELE_SYSTEM_ERROR) :
                                authentication.getPrincipal() == null ?
                                        new EleUserException("getPrincipal为空", ELE_SYSTEM_ERROR) :
                                        new EleUserException("userId为空", ELE_SYSTEM_ERROR)
                );
    }


    //注册，插入用户信息


}
