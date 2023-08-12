package com.hzau.InsertToMysql.utils;

import com.hzau.InsertToMysql.mappers.SqlMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils<T> {
    private static final SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    private static final SqlSessionFactory sqlSessionFactory;
    /*
    SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
    使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，
    多次重建 SqlSessionFactory 被视为一种代码“坏习惯”。
    最简单的就是使用单例模式或者静态单例模式。
    */
    private MybatisUtils(){}
    static {
        sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=build();
    }
    private static SqlSessionFactory build(){
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sqlSessionFactoryBuilder.build(resourceAsStream);
    }
    public static SqlSession getSqlSession(){
		/*
		每个线程都应该有它自己的 SqlSession 实例。
		SqlSession 的实例不是线程安全的，因此是不能被共享的每个线程都应该有它自己的 SqlSession 实例。
		SqlSession 的实例不是线程安全的，因此是不能被共享的
		 */
        return sqlSessionFactory.openSession();
    }
}
