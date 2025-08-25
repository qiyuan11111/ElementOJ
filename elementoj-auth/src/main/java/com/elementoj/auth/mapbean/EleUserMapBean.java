package com.elementoj.auth.mapbean;


import com.elementoj.api.system.bo.EleUserBO;
import com.elementoj.api.system.dto.query.EleUserRegisterDTO;
import com.elementoj.auth.domain.EleUser;
import com.elementoj.auth.domain.dto.EleUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EleUserMapBean {

    EleUserBO toEleUserBO(EleUserRegisterDTO eleUserRegisterDTO);

    EleUserBO toEleUserBO(EleUserDTO eleUserDTO);

    EleUser toEleUserDO(EleUserBO eleUserBO);
}
