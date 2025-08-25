package com.elementoj.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.elementoj.api.system.bo.EleUserBO;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.auth.domain.dto.EleUserDTO;
import com.elementoj.auth.mapbean.EleUserMapBean;
import com.elementoj.auth.mapper.EleUserMapper;
import com.elementoj.auth.service.IEleUserService;
import com.elementoj.common.core.web.constant.UserConstants;
//import com.elementoj.common.security.utils.SecurityUtils;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

@Service
public class EleUserService implements UserDetailsService, IEleUserService {

    private final EleUserMapper eleUserMapper;

    private final PasswordEncoder passwordEncoder;

    private final EleUserMapBean eleUserMapBean;

    public EleUserService(EleUserMapper eleUserMapper, PasswordEncoder passwordEncoder, EleUserMapBean eleUserMapBean) {
        this.eleUserMapper = eleUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.eleUserMapBean = eleUserMapBean;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EleUserDTO userDTO = eleUserMapper.selectUserDetailsByUserName(username);
        if (ObjectUtil.isNull(userDTO)){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return eleUserMapBean.toEleUserBO(userDTO);
    }

    @Override
    public void register(EleUserBO eleUserBO) {
        Stream.<Supplier<EleUserException>>of(
                        () -> StrUtil.isBlank(eleUserBO.getUsername()) ?
                                new EleUserException("用户名不能为空", ELE_USER_NONE_USERNAME) : null,

                        () -> StrUtil.isBlank(eleUserBO.getPassword()) ?
                                new EleUserException("密码不能为空", ELE_USER_NONE_PASSWORD) : null,

                        () -> {
                            int pwdLen = eleUserBO.getPassword().length();
                            return (pwdLen < UserConstants.MinPasswordLength || pwdLen > UserConstants.MaxPasswordLength) ?
                                    new EleUserException("密码长度必须在" + UserConstants.MinPasswordLength + "到" +
                                            UserConstants.MaxPasswordLength + "之间", ELE_USER_IRREGULAR_PASSWORD) : null;
                        },

                        () -> {
                            int nameLen = eleUserBO.getUsername().length();
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

        eleUserBO.setPassword(passwordEncoder.encode(eleUserBO.getPassword()));

        Long count = eleUserMapper.getUserCountByUserName(eleUserBO.getUsername());
        if (count > 0)
            throw new EleUserException("用户已存在", ELE_USER_MULTIPLE_USERS);

        int result = eleUserMapper.registerUser(eleUserMapBean.toEleUserDO(eleUserBO));
        if (result == 0)
            throw new EleUserException("注册失败", ELE_USER_REGISTER_FAILED);
    }

//    public EleUser getUserByToken(Authentication authentication) {
//        return Optional.ofNullable(authentication)
//                .map(auth -> (EleUserDetails) auth.getPrincipal())
//                .map(EleUserDetails::getUserId)
//                .map(userId -> eleUserMapper.getUserNameAndIdByUserId(userId))
//                .orElseThrow(() ->
//                        authentication == null ?
//                                new EleUserException("authentication为空", ELE_SYSTEM_ERROR) :
//                                authentication.getPrincipal() == null ?
//                                        new EleUserException("getPrincipal为空", ELE_SYSTEM_ERROR) :
//                                        new EleUserException("userId为空", ELE_SYSTEM_ERROR)
//                );
//    }


    //注册，插入用户信息


}
