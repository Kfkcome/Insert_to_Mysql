package com.hzau.InsertToMysql.mappers;

import com.hzau.InsertToMysql.domain.Species;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

;

@Mapper
public interface SpeciesMapper {
    List<Species> findAll();

    Integer findByName(String name);

    int addSpecies(Species species);
}
