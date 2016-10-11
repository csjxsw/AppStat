package com.cmdi.service;

import java.util.List;
import java.util.Map;

import com.cmdi.dao.GPDao;
import com.cmdi.dao.GpDaoImpl;
import com.cmdi.model.AppModel;

public class GPService {

	private GPDao dao = new GpDaoImpl();
	
	public int computeDisturbCellTotalForCity(String mapperId,
			Map<String, Object> map) {
		return this.dao.computeDisturbCellTotalForCity(mapperId, map);
	}

	public void deleteMRDataByScenario(Map<String, Object> map) {
		this.dao.deleteMRDataByScenario(map);
	}

	public int computeValidCellNumForProvinceByScenario(String mapperId,
			Map<String, Object> map) {
		return this.dao.computeValidCellNumForProvinceByScenario(mapperId, map);
	}

	public int computeValidIndoorCellNumForProvince(String mapperId,
			Map<String, Object> map) {
		return this.dao.computeValidIndoorCellNumForProvince(mapperId, map);
	}

	public int computeValidIndoorCellNumForProvinceByScenario(String mapperId,
			Map<String, Object> map) {
		return this.dao.computeValidIndoorCellNumForProvinceByScenario(mapperId, map);
	}

	public List<String> getAllAppNameByMonth(String mapperId, Map<String, Object> map) {
		return this.dao.getAllAppNameByMonth(mapperId, map);
	}

	public List<AppModel> getAppDataByAppNameAndDate(String mapperId, Map<String, Object> map) {
		return this.dao.getAppDataByAppNameAndDate(mapperId, map);
	}

	public List<String> getAllCountryForAppName(String mapperId,
			Map<String, Object> map) {
		return this.dao.getAllCountryForAppName(mapperId, map);
	}
}
