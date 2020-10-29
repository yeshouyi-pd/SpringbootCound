package com.pd.server.mapper.my;

import com.pd.server.dto.ResourceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyUserMapper {

    List<ResourceDto> findResources(@Param("rode") String rode);

}
