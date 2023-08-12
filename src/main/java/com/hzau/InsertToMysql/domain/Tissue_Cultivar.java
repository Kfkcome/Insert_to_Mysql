package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组织品种
 * 是确定品种和组织之间的关系的pojo类
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tissue_Cultivar {
    int TISSUE_ID;
    int CULTIVAR_ID;
}
