package com.hzau.InsertToMysql.mappers;

import com.hzau.InsertToMysql.domain.Software;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SoftwareMapper {
    Software selectByName(@Param("SOFTWARE_NAME") String SOFTWARE_NAME);
}
