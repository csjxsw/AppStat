package com.cmdi.dao;

import java.util.List;
import java.util.Map;



import com.cmdi.model.AppModel;

public abstract interface GPDao {
	public abstract Map<String, Map<String, Long>> getProvinceCGINum(String mapperId, String importdate);
	public abstract Map<String, Map<String, Long>> getProvinceIndoorNum(String mapperId, String importdate);

	public abstract void insertData(String mapperId, Map<String, Object> paramMap);
	public abstract int computeDisturbCellTotalForCity(String mapperId,
			Map<String, Object> map);
	public abstract int computeDisturbCellTotalForProvince(String mapperId,
			Map<String, Object> map);
	public abstract void deleteMRDataByScenario(Map<String, Object> map);
	public abstract int computeValidCellNumForProvinceByScenario(
			String mapperId, Map<String, Object> map);
	public abstract int computeValidIndoorCellNumForProvince(String mapperId,
			Map<String, Object> map);
	public abstract int computeValidIndoorCellNumForProvinceByScenario(
			String mapperId, Map<String, Object> map);
	public abstract List<String> getAllAppNameByMonth(String mapperId,
			Map<String, Object> map);
	public abstract List<AppModel> getAppDataByAppNameAndDate(String mapperId,
			Map<String, Object> map);
	public abstract List<String> getAllCountryForAppName(String mapperId,
			Map<String, Object> map);
	
}
