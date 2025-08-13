package com.elementoj.api.system.mapbean;


import com.elementoj.api.system.bo.EleUserBO;
import com.elementoj.api.system.domain.EleUser;
import com.elementoj.api.system.dto.query.EleUserRegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EleUserMapBean {
    EleUserBO toEleUserBO(EleUserRegisterDTO eleUserRegisterDTO);

    EleUser toEleUserDO(EleUserBO eleUserBO);
}
