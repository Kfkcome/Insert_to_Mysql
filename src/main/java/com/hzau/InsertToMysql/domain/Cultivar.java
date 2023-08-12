package com.hzau.InsertToMysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cultivar {
    int CULTIVAR_ID;
    String CULTIVAR_NAME;
    int SPECIES_ID;
    int CS_NUM;

    public Cultivar(String CULTIVAR_NAME, int SPECIES_ID) {
        this.CULTIVAR_NAME = CULTIVAR_NAME;
        this.SPECIES_ID = SPECIES_ID;
    }
}
