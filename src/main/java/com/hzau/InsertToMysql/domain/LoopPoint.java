package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Loop点的实体类
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoopPoint {
    long IC_ID;
    int CS_ID;
    long START_POINT1;
    long END_POINT1;
    long START_POINT2;
    long END_POINT2;
    int IC_NUM;
    double FDR_VALUE;
    int TISSUE_ID;
    int SOFTWARE_ID;
}
