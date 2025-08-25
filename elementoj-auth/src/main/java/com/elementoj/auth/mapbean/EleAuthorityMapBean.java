package com.elementoj.auth.mapbean;

import com.elementoj.api.system.bo.EleAuthorityBO;
import com.elementoj.auth.domain.EleAuthority;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EleAuthorityMapBean {
    EleAuthority toEleAuthorityDO(EleAuthority eleAuthorityBO);

    EleAuthorityBO toEleAuthorityBO(EleAuthority eleAuthorityDO);

    List<EleAuthorityBO> toEleAuthorityBOs(List<EleAuthority> eleAuthorityDOs);
}
