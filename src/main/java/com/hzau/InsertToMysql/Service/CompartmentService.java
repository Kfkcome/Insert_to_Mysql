package com.hzau.InsertToMysql.Service;

import com.hzau.InsertToMysql.domain.ChromosomeT;
import com.hzau.InsertToMysql.domain.CompartmentPoint;
import com.hzau.InsertToMysql.domain.Cultivar;
import com.hzau.InsertToMysql.mappers.*;
import com.hzau.InsertToMysql.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CompartmentService {
    SqlSession sqlSession;
    ChromosomeMapper chromosomeMapper;
    CompartmentMapper compartmentMapper;
    SpeciesMapper speciesMapper;
    CultivarMapper cultivarMapper;
    TissueMapper tissueMapper;
    SoftwareMapper softwareMapper;

    public CompartmentService() {
        sqlSession = MybatisUtils.getSqlSession();
        chromosomeMapper = sqlSession.getMapper(ChromosomeMapper.class);
        compartmentMapper = sqlSession.getMapper(CompartmentMapper.class);
        speciesMapper = sqlSession.getMapper(SpeciesMapper.class);
        cultivarMapper = sqlSession.getMapper(CultivarMapper.class);
        tissueMapper = sqlSession.getMapper(TissueMapper.class);
        softwareMapper = sqlSession.getMapper(SoftwareMapper.class);
    }

    public int addPointFromFile(String path) {
        int CultivarID;//品种id
        int SpeciesID;//物种id
        int TISSUE_ID;//组织id
        int SOFTWARE_ID;//软件id
        File file = new File(path);
        String name = file.getName();//文件名称
        String[] split1 = name.split("\\.")[0].split("_");//获取文件名除去后缀后的名称

        SpeciesID = speciesMapper.findByName(split1[0]);
        CultivarID = cultivarMapper.findByName_SpeciesID(new Cultivar(split1[1], SpeciesID));
        TISSUE_ID = tissueMapper.selectByTissueName(split1[2]).getTISSUE_ID();
        SOFTWARE_ID = softwareMapper.selectByName(split1[3]).getSOFTWARE_ID();
        ArrayList<CompartmentPoint> compartmentPointArrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] split = data.split("\t");
                ChromosomeT byCultivarIDCsName = chromosomeMapper.findByCultivarID_CSName(CultivarID,
                        split[0].trim()/*染色体的名字*/);
                if (!split[3].equals("nan")) {
                    compartmentPointArrayList.add(new CompartmentPoint(1
                            , byCultivarIDCsName.getCS_ID()
                            , Long.parseLong(split[1]), Long.parseLong(split[2])
                            , Double.parseDouble(split[3]), SOFTWARE_ID, TISSUE_ID));
                } else {
                    compartmentPointArrayList.add(new CompartmentPoint(1
                            , byCultivarIDCsName.getCS_ID()
                            , Long.parseLong(split[1]), Long.parseLong(split[2])
                            , 0, SOFTWARE_ID, TISSUE_ID));
                }
            }
            compartmentMapper.insertFromFile(compartmentPointArrayList);
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return compartmentPointArrayList.size();
    }
}
