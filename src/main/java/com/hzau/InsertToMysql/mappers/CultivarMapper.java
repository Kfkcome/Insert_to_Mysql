package com.hzau.InsertToMysql.mappers;


import com.hzau.InsertToMysql.domain.Cultivar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CultivarMapper {
    int addCultivar(Cultivar cultivar);

    List<Cultivar> findAll();

    List<Cultivar> findBCLABySpeciesID(@Param("SPECIES_ID") int SPECIES_ID);

    int findByName_SpeciesID(Cultivar cultivar);

    Integer updateCSNum(@Param("CS_NUM") int CS_NUM, @Param("CULTIVAR_ID") int CULTIVAR_ID);

}
