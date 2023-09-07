package com.hzau.InsertToMysql.Service;

import com.hzau.InsertToMysql.domain.Cultivar;
import com.hzau.InsertToMysql.domain.RNA;
import com.hzau.InsertToMysql.domain.RNA_STRUCTURE;
import com.hzau.InsertToMysql.mappers.*;
import com.hzau.InsertToMysql.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RnaService {
    private final SqlSession sqlSession;
    private final ChromosomeMapper chromosomeMapper;
    private final SpeciesMapper speciesMapper;
    private final CultivarMapper cultivarMapper;
    private final TissueMapper tissueMapper;

    private final RnaMapper rnaMapper;

    public RnaService() {
        sqlSession = MybatisUtils.getSqlSession();
        rnaMapper = sqlSession.getMapper(RnaMapper.class);
        chromosomeMapper = sqlSession.getMapper(ChromosomeMapper.class);
        speciesMapper = sqlSession.getMapper(SpeciesMapper.class);
        cultivarMapper = sqlSession.getMapper(CultivarMapper.class);
        tissueMapper = sqlSession.getMapper(TissueMapper.class);
    }

    private int checkDirection(String s) {
        if (s.equals("-")) {
            return 0;
        }
        return 1;
    }

    public Integer insertRnaFromFile(String path) {
        System.out.println("进入了方法");
        int CultivarID;//品种id
        int SpeciesID;//物种id
        // int TISSUE_ID;//组织id
        Map<String, Integer> saveExonNum = new HashMap<>();
        Map<String, Integer> saveRnaId = new HashMap<>();//因为一个品种中的名称唯一所以可以用来存id，避免查询花费大量时间
        ArrayList<RNA> rnaArrayList = new ArrayList<>();
        ArrayList<RNA_STRUCTURE> rnaStructures = new ArrayList<>();
        File file = new File(path);
        String name = file.getName();//文件名称
        String[] split1 = name.split("\\.")[0].split("_");//获取文件名除去后缀后的名称
        try {
            System.out.println(split1[0]);
            SpeciesID = speciesMapper.findByName(split1[0]);
            System.out.println(SpeciesID);
            CultivarID = cultivarMapper.findByName_SpeciesID(new Cultivar(split1[1], SpeciesID));
            System.out.println("品种id" + CultivarID);
            //TISSUE_ID = tissueMapper.selectByTissueName(split1[2]).getTISSUE_ID();
            //System.out.println(TISSUE_ID);
        } catch (NullPointerException e) {
            System.out.println("文件出错，查询出错");
            sqlSession.close();
            return 0;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String s;
            int max_id = rnaMapper.getMaxID() + 1;
            int line = 0;
            while ((s = bufferedReader.readLine()) != null) {
                line++;
                //System.out.print(line + ' ');
                String[] split = s.split("\t");
                int CS_ID;
                try {
                    CS_ID = chromosomeMapper.findByCultivarID_CSName(CultivarID, split[0].trim()).getCS_ID();
                } catch (NullPointerException e) {
                    System.out.println("查询错误" + "物种id" + CultivarID + "要查询的染色体名字：" + split[0].trim() + "出错行数：" + line);
                    return 0;
                }
                long START_POINT = Long.parseLong(split[3]);
                long END_POINT = Long.parseLong(split[4]);
                if (split[2].equals("mRNA")) {
                    String mRNAName = split[8].split("=")[1].split(";")[0];
                    int direction = checkDirection(split[6]);
                    RNA rna = new RNA(CS_ID, max_id, mRNAName, START_POINT, END_POINT, direction);
                    rnaArrayList.add(rna);
                    saveExonNum.put(mRNAName, 0);
                    saveRnaId.put(mRNAName, max_id);
                    max_id++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Integer i = rnaMapper.insertRnaFromFile(rnaArrayList);
        sqlSession.commit();
        System.out.println("插入了这么多rna" + i);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] split = s.split("\t");
                if (split[2].equals("exon")) {
                    long START_POINT = Long.parseLong(split[3]);
                    long END_POINT = Long.parseLong(split[4]);
                    String mRNA_NAME = split[8].split("Parent=")[1].split(";")[0];
                    int mRna_ID;
                    try {
                        mRna_ID = saveRnaId.get(mRNA_NAME);//从缓存中获取mRnaID
                    } catch (NullPointerException e) {
                        System.out.println("查询不到这个 rna" + mRNA_NAME);
                        continue;
                    }
                    Integer temp = saveExonNum.get(mRNA_NAME);
                    int EXON_ID = temp + 1;
                    saveExonNum.replace(mRNA_NAME, EXON_ID);
                    RNA_STRUCTURE rnaStructure = new RNA_STRUCTURE(mRna_ID, EXON_ID, START_POINT, END_POINT);
                    rnaStructures.add(rnaStructure);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (rnaStructures.size() >= 1)
            i += rnaMapper.insertRna_StructureFromFile(rnaStructures);
        else System.out.println("该文件没有exon选项");
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

}