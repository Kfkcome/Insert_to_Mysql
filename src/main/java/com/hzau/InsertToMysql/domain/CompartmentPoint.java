package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompartmentPoint {
    int ID;
    int CS_ID;
    long START_POINT;
    long END_POINT;
    double VALUE;
    int SOFTWARE_ID;
    int TISSUE_ID;
}
