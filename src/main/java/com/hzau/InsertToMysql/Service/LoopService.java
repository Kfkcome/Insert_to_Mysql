package com.hzau.InsertToMysql.Service;

import com.hzau.InsertToMysql.domain.ChromosomeT;
import com.hzau.InsertToMysql.domain.Cultivar;
import com.hzau.InsertToMysql.domain.LoopPoint;
import com.hzau.InsertToMysql.mappers.*;
import com.hzau.InsertToMysql.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoopService {
    SqlSession sqlSession;
    LoopMapper loopMapper;
    ChromosomeMapper chromosomeMapper;
    SpeciesMapper speciesMapper;
    CultivarMapper cultivarMapper;
    TissueMapper tissueMapper;
    SoftwareMapper softwareMapper;

    public LoopService() {
        sqlSession = MybatisUtils.getSqlSession();
        loopMapper = sqlSession.getMapper(LoopMapper.class);
        chromosomeMapper = sqlSession.getMapper(ChromosomeMapper.class);
        speciesMapper = sqlSession.getMapper(SpeciesMapper.class);
        cultivarMapper = sqlSession.getMapper(CultivarMapper.class);
        tissueMapper = sqlSession.getMapper(TissueMapper.class);
        softwareMapper = sqlSession.getMapper(SoftwareMapper.class);
    }

    public int insertFromOneFile(String path) {
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

        ArrayList<LoopPoint> arrayList = new ArrayList<>();
        int i = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] split = s.split("\t");
                //根据物种id和染色体名查询染色体id
                ChromosomeT byCultivarIDCsName = chromosomeMapper.findByCultivarID_CSName(CultivarID, split[0].trim());
                if (byCultivarIDCsName == null) {
                    System.out.println("查询不到染色体：" + split[0] + "跳过本条");
                    continue;
                }
                LoopPoint loopPoint = new LoopPoint(0, byCultivarIDCsName.getCS_ID(), Long.parseLong(split[1]),
                        Long.parseLong(split[2]), Long.parseLong(split[4]), Long.parseLong(split[5]), (int) Double.parseDouble(split[6])
                        , Double.parseDouble(split[7]), TISSUE_ID, SOFTWARE_ID);
                arrayList.add(loopPoint);
                i++;
            }
            loopMapper.insertFromFile(arrayList);
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayList.size();
    }
}
