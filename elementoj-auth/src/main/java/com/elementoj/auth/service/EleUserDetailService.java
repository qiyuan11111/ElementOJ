package com.elementoj.auth.service;

import com.elementoj.auth.mapper.EleUserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EleUserDetailService implements UserDetailsService {
    @Resource
    EleUserDetailsMapper eleUserDetailsMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = eleUserDetailsMapper.selectUserDetailsByUserName(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return userDetails;
    }
}
