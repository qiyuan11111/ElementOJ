package com.elementoj.auth.service.impl;

import com.elementoj.auth.mapper.EleUserMapper;
import com.elementoj.auth.service.IEleUserDetailService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EleUserDetailService implements UserDetailsService, IEleUserDetailService {
    @Resource
    EleUserMapper eleUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = eleUserMapper.selectUserDetailsByUserName(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return userDetails;
    }
}
