package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * chromosome 染色体
 * 防止与java-straw中的Chromosome重名
 *
 * @author kfk
 * @date 2023/07/09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChromosomeT {
    int CS_ID;
    String CS_NAME;
    long CS_LENGTH;
    int CULTIVAR_ID;
    int TISSUE_ID;//组织id
    int SOFTWARE_ID;//生成软件id
}
