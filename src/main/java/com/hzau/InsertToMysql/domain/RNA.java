package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RNA的实体类
 * 与GFF文件相关
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RNA {
    int CS_ID;
    int mRNA_ID;

    String mRNA_NAME;
    long START_POINT;
    long END_POINT;
    int DIRECTION;//方向
    int TISSUE_ID;
}
