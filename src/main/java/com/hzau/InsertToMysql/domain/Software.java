package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 软件类型
 * 数据来源有不同的软件类型
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Software {
    int SOFTWARE_ID;
    String SOFTWARE_NAME;
}
