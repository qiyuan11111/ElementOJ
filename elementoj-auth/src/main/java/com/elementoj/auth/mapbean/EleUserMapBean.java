package com.elementoj.auth.mapbean;


import com.elementoj.api.system.bo.EleUserBO;
import com.elementoj.api.system.dto.query.EleUserRegisterDTO;
import com.elementoj.auth.domain.EleUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EleUserMapBean {

    EleUserBO toEleUserBO(EleUserRegisterDTO eleUserRegisterDTO);

    EleUser toEleUserDO(EleUserBO eleUserBO);
}
