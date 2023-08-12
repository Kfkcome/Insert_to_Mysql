package com.hzau.InsertToMysql.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SqlMapper {
    /**
     * @param uid
     * @param username
     * @return
     */
    int addUser(@Param("uid") String uid, @Param("username") String username);
}

