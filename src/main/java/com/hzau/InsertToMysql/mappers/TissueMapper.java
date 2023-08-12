package com.hzau.InsertToMysql.mappers;

import com.hzau.InsertToMysql.domain.Tissue;
import com.hzau.InsertToMysql.domain.Tissue_Cultivar;
import org.apache.ibatis.annotations.Param;

public interface TissueMapper {
    Tissue selectByTissueName(@Param("TissueName") String tissueName);
    int insertToTissue_Cultivar(Tissue_Cultivar tissueCultivar);
}
