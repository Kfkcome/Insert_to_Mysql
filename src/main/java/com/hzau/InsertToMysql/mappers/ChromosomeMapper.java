package com.hzau.InsertToMysql.mappers;

import com.hzau.InsertToMysql.domain.ChromosomeT;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChromosomeMapper {
    int insertChromosome(List<ChromosomeT> chromosomeT);

    List<ChromosomeT> findMaxLength(@Param("CULTIVATE_ID") int CULTIVATE_ID, @Param("limit") int limit);

    List<ChromosomeT> findAll();

    List<ChromosomeT> findByCultivarID(@Param("id") int id);

    ChromosomeT findByCultivarID_CSName(@Param("id") int id, @Param("name") String name);

    ChromosomeT findByCS_ID(@Param("cs_id") int cs_id);

}
