package com.hzau.InsertToMysql.mappers;

import com.hzau.InsertToMysql.domain.RNA;
import com.hzau.InsertToMysql.domain.RNA_STRUCTURE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RnaMapper {
    Integer insertRnaFromFile(List<RNA> rna);

    Integer insertRna_StructureFromFile(List<RNA_STRUCTURE> rnaStructures);

    Integer getMaxID();

    RNA selectRnaByID(@Param("mRNA_NAME") String mRNA_NAME, @Param("CS_ID") int CS_ID);

    List<RNA_STRUCTURE> selectRnaStructByID(@Param("mRNA_ID") int mRnaId);

    List<RNA> selectRnaByCS_ID_START_END(@Param("CS_ID") int cs_id, @Param("START_POINT") long startPoint
            , @Param("END_POINT") long endPoint);

    List<RNA> selectRnaByCS_ID_START_END_One(@Param("CS_ID") int cs_id, @Param("START_POINT") long startPoint
            , @Param("END_POINT") long endPoint);

    List<RNA> selectRnaByCS_ID(@Param("CS_ID") int cs_id);

    List<RNA> selectRnaByCS_ID_One(@Param("CS_ID") int cs_id);
}
