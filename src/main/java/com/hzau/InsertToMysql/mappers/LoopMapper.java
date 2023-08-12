package com.hzau.InsertToMysql.mappers;

import com.hzau.InsertToMysql.domain.LoopPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoopMapper {
    int insertFromFile(List<LoopPoint> item);
}
