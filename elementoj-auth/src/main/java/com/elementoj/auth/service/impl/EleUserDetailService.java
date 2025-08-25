package com.elementoj.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.elementoj.auth.domain.dto.EleUserDTO;
import com.elementoj.auth.mapbean.EleUserMapBean;
import com.elementoj.auth.mapper.EleUserMapper;
import com.elementoj.auth.service.IEleUserDetailService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Deprecated
//@Service
public class EleUserDetailService implements UserDetailsService, IEleUserDetailService {
    private final EleUserMapper eleUserMapper;

    private final EleUserMapBean eleUserMapBean;

    public EleUserDetailService(EleUserMapper eleUserMapper, EleUserMapBean eleUserMapBean) {
        this.eleUserMapper = eleUserMapper;
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
}
