package com.cmdi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.cmdi.model.AppModel;

public class GpDaoImpl implements GPDao {

	public void insertData(String mapperId, Map<String, Object> paramMap) {

		SqlSessionFactory sqlSessionFactory = GpSessionUtils
				.getSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int successNum = sqlSession.insert(mapperId, paramMap);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public Map<String, Map<String, Long>> getProvinceCGINum(String mapperId,
			String importdate) {
		SqlSessionFactory sqlSessionFactory = GpSessionUtils.getSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectMap(mapperId, importdate, "province");
	}

	public Map<String, Map<String, Long>> getProvinceIndoorNum(String mapperId,
			String importdate) {
		SqlSessionFactory sqlSessionFactory = GpSessionUtils.getSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectMap(mapperId, importdate, "province");
	}

	public int computeDisturbCellTotalForCity(String mapperId,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int computeDisturbCellTotalForProvince(String mapperId,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteMRDataByScenario(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	public int computeValidCellNumForProvinceByScenario(String mapperId,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int computeValidIndoorCellNumForProvince(String mapperId,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int computeValidIndoorCellNumForProvinceByScenario(String mapperId,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<String> getAllAppNameByMonth(String mapperId,
			Map<String, Object> map) {
		SqlSessionFactory sqlSessionFactory = GpSessionUtils.getSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList(mapperId, map);
	}

	public List<AppModel> getAppDataByAppNameAndDate(String mapperId,
			Map<String, Object> map) {
		SqlSessionFactory sqlSessionFactory = GpSessionUtils.getSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList(mapperId, map);
	}

	public List<String> getAllCountryForAppName(String mapperId,
			Map<String, Object> map) {
		SqlSessionFactory sqlSessionFactory = GpSessionUtils.getSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList(mapperId, map);
	}

}
