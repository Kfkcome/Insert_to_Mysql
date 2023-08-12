package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物种
 * 物种下一层是品种
 * 同一物种有多个品种
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Species {
    int SPECIES_ID;
    String SPECIES_NAME;
}
