package com.hzau.InsertToMysql.Service;

import com.hzau.InsertToMysql.domain.Cultivar;
import com.hzau.InsertToMysql.domain.Tissue;
import com.hzau.InsertToMysql.domain.Tissue_Cultivar;
import com.hzau.InsertToMysql.mappers.CultivarMapper;
import com.hzau.InsertToMysql.mappers.SpeciesMapper;
import com.hzau.InsertToMysql.mappers.TissueMapper;
import com.hzau.InsertToMysql.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TissueService {
    SqlSession sqlSession;
    CultivarMapper cultivarMapper;
    SpeciesMapper speciesMapper;
    TissueMapper tissueMapper;

    public TissueService() {
        sqlSession = MybatisUtils.getSqlSession();
        speciesMapper = sqlSession.getMapper(SpeciesMapper.class);
        cultivarMapper = sqlSession.getMapper(CultivarMapper.class);
        tissueMapper = sqlSession.getMapper(TissueMapper.class);
    }

    public void addToTissue_Cultivar(String path) {
        File file = new File(path);
        String s;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((s = bufferedReader.readLine()) != null) {
                String[] split = s.split("\t");
                Integer byName = speciesMapper.findByName(split[0].trim());
                System.out.println(split[0] + "  " + split[1] + "  " + split[2] + "  ");
                Cultivar cultivar = new Cultivar();
                cultivar.setCULTIVAR_NAME(split[1].trim());
                cultivar.setSPECIES_ID(byName);
                //查询到品种的id
                int byNameSpeciesID = cultivarMapper.findByName_SpeciesID(cultivar);
                Tissue tissue = tissueMapper.selectByTissueName(split[2].trim());
                Tissue_Cultivar tissueCultivar = new Tissue_Cultivar(tissue.getTISSUE_ID(), byNameSpeciesID);
                tissueMapper.insertToTissue_Cultivar(tissueCultivar);
                sqlSession.commit();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
