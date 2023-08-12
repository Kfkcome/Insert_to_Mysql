package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Compartment点
 * 单位是MB
 *
 * @author kfk
 * @date 2023/08/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompartmentPointMB {
    int ID;
    int CS_ID;
    double START_POINT;
    double END_POINT;
    double VALUE;

    public CompartmentPointMB(CompartmentPoint compartmentPoint) {
        this.ID = compartmentPoint.getID();
        this.CS_ID = compartmentPoint.getCS_ID();
        this.VALUE = compartmentPoint.getVALUE();
        this.END_POINT = compartmentPoint.getEND_POINT() / 1000000.0;
        this.START_POINT = compartmentPoint.getSTART_POINT() / 1000000.0;
    }
}
