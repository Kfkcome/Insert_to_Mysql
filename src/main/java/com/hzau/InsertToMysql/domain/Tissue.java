package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组织
 * 一个品种下有多个组织
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tissue {
    int TISSUE_ID;
    String TISSUE_NAME;
}
