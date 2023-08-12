package com.hzau.InsertToMysql.mappers;


import com.hzau.InsertToMysql.domain.CompartmentPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompartmentMapper {
    int insertFromFile(List<CompartmentPoint> compartmentPoints);

    List<CompartmentPoint> findCompartmentID(@Param("cs_id") int cs_id);

    List<CompartmentPoint> findCompartmentEND_START(@Param("cs_id") int cs_id
            , @Param("start") int start, @Param("end") int end);
}
