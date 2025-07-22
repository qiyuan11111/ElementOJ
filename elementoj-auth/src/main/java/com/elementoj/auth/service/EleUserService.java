package com.elementoj.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elementoj.api.system.domain.EleUser;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.auth.mapper.EleUserMapper;
import com.elementoj.common.core.web.constant.UserConstants;
import com.elementoj.common.security.utils.SecurityUtils;
import com.elementoj.common.core.web.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

@Service
public class EleUserService {

    @Resource
    private EleUserMapper eleUserMapper;

    public void register(EleUser user) {

        if (StringUtils.isBlank(user.getUserName()))
            throw new EleUserException("用户名不能为空", ELE_USER_NONE_USERNAME);

        if (StringUtils.isBlank(user.getPassword()))
            throw new EleUserException("密码不能为空", ELE_USER_NONE_PASSWORD);

        if (UserConstants.MinPasswordLength > user.getPassword().length() || UserConstants.MaxPasswordLength < user.getPassword().length())
            throw new EleUserException("密码长度必须在" + UserConstants.MinPasswordLength + "到" + UserConstants.MaxPasswordLength + "之间", ELE_USER_IRREGULAR_PASSWORD);

        if (UserConstants.MinUserNameLength > user.getUserName().length() || UserConstants.MaxUserNameLength < user.getUserName().length())
            throw new EleUserException("用户名长度必须在" + UserConstants.MinUserNameLength + "到" + UserConstants.MaxUserNameLength + "之间", ELE_USER_IRREGULAR_USERNAME);

        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        Long count = getUserCountByUserName(user.getUserName());
        if (count > 0)
            throw new EleUserException("用户已存在", ELE_USER_MULTIPLE_USERS);

        int result = registerUser(user);
        if (result == 0)
            throw new EleUserException("注册失败", ELE_USER_REGISTER_FAILED);
    }

    public Long getUserCountByUserName(String userName) {
        return eleUserMapper.selectCount(
                new QueryWrapper<EleUser>()
                        .eq("user_name", userName)
        );
    }

    //注册，插入用户信息
    public int registerUser(EleUser user) {
        return eleUserMapper.insert(user);
    }

    public EleUser getUserByUserId(Long userId){
        return eleUserMapper.selectOne(
                new QueryWrapper<EleUser>()
                        .select("user_id", "user_name", "nick_name", "email")
                        .eq("user_id", userId)
        );
    }


}
