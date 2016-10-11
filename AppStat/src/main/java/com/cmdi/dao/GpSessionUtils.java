package com.cmdi.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GpSessionUtils {
	private static SqlSessionFactory sqlSessionFactory=null;
	private static Reader reader;
	
	static{
		try{
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			if(sqlSessionFactory == null){
				synchronized(GpSessionUtils.class){
					if(sqlSessionFactory == null){
						sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSessionFactory(){
		return sqlSessionFactory;
	}
}
