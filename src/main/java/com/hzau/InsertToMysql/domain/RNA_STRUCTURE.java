package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rna结构
 * 与Gff图相关
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RNA_STRUCTURE {
    int mRNA_ID;
    int EXON_ID;
    long START_POINT;
    long END_POINT;
}
