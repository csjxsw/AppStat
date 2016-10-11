package com.cmdi.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cmdi.model.AppModel;
import com.cmdi.service.GPService;
import com.cmdi.util.BasicCalUtil;
import com.csvreader.CsvWriter;

public class ComputeAppThread implements Runnable {
	private String year;
	private String month;
	private CsvWriter cwriter;
	
	public ComputeAppThread(String year,String month,CsvWriter cwriter) {
		super();
		this.year = year;
		this.month = month;
		this.cwriter = cwriter;
	}

	@Override
	public void run() {
		try {
			System.out.println("year="+year+",month="+month+",cwriter="+cwriter);
			getdata();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private  void getdata() throws IOException {
		String time = year + "-" + month + "%";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", time);

		GPService gp = new GPService();
		List<String> appNameList = gp.getAllAppNameByMonth(
				"getAllAppNameByMonth", map);

		for (String appName : appNameList) {
			map.put("appName", appName);
			List<AppModel> appList = gp.getAppDataByAppNameAndDate(
					"getAppDataByAppNameAndDate", map);
			appdatastat(appList);
		}

	}

	private void appdatastat(List<AppModel> appList) throws IOException {
		System.out.println("appList.size()="+appList.size());
		double indicator[] = getIndicatorStat(appList);
		AppModel appData = appList.get(0);
		String date = appData.getDate();
		date = date.split("-")[0] + date.split("-")[1];
		String appId = appData.getAppId();
		String appName = appData.getAppName();
		String category = appData.getCategory();
		String subCategory = appData.getSubCategory();
		String categoryRank = appData.getCategoryRank();
		
		System.out.println("appName="+appName+"---->"+"overrall" + ":" + indicator[0]+","+
				"subcategoryRank" + ":" + indicator[1]+","+
				"totalRating" + ":" + indicator[2]);
		
		String[] strList = new String[] { date, appId, appName, category,
				subCategory, String.valueOf(indicator[0]), categoryRank,
				String.valueOf(indicator[1]), String.valueOf(indicator[2]),
				String.valueOf(indicator[3]), String.valueOf(indicator[4]),
				String.valueOf(indicator[5]), String.valueOf(indicator[6]),
				String.valueOf(indicator[7]) };

		synchronized(cwriter){
			cwriter.writeRecord(strList, true);
//		cwriter.endRecord();// 换行
			cwriter.flush();// 刷新数据
		}
	}

	private double[] getIndicatorStat(List<AppModel> appList) {
		double res[] = new double[8];
		double overrallRankSum = 0, overrallRankNum = 0;
		double subCategoryRankSum = 0, subCategoryRankNum = 0;
		double totalRatingSum = 0, totalRatingNum = 0;
		double averageRatingSum = 0, averageRatingNum = 0;
		double noofDownLoadsSum = 0;
		double downloadRevenueSum = 0, downloadRevenueNum = 0;
		double inAppPurcharseRevenueSum = 0, inAppPurcharseRevenueNum = 0;
		double advertistingRevenuesSum = 0;

		for (AppModel app : appList) {
			String overrallRank = app.getOverrallRank();
			String subCategoryRank = app.getSubCategoryRank();
			String totalRating = app.getTotalRating();
			String averageRating = app.getAverageRating();
			String noofDownLoads = app.getNumOfDownLoads();
			String downloadRevenue = app.getDownLoadRevenue();
			String inAppPurcharseRevenue = app.getInApp_purchase_revenue();
			String advertistingRevenues = app.getAdvertistingRevenues();

			if (!overrallRank.equals("n/a")) {
				overrallRankSum += Double.parseDouble(overrallRank);
				overrallRankNum = overrallRankNum + 1;
			}
			if (!subCategoryRank.equals("n/a")) {
				subCategoryRankSum += Double.parseDouble(subCategoryRank);
				subCategoryRankNum = subCategoryRankNum + 1;
			}

			if (!totalRating.equals("n/a")) {
				totalRatingSum += Double.parseDouble(totalRating);
				totalRatingNum = totalRatingNum + 1;
			}
			if (!averageRating.equals("n/a")) {
				averageRatingSum += Double.parseDouble(averageRating);
				averageRatingNum = averageRatingNum + 1;
			}

			if (!noofDownLoads.equals("n/a")) {
				noofDownLoadsSum += Double.parseDouble(noofDownLoads);
			}
			if (!downloadRevenue.equals("n/a")) {
				downloadRevenueSum += Double.parseDouble(downloadRevenue);
				downloadRevenueNum = downloadRevenueNum + 1;
			}

			if (!inAppPurcharseRevenue.equals("n/a")) {
				inAppPurcharseRevenueSum += Double
						.parseDouble(inAppPurcharseRevenue);
				inAppPurcharseRevenueNum = inAppPurcharseRevenueNum + 1;
			}
			if (!advertistingRevenues.equals("n/a")) {
				advertistingRevenuesSum += Double
						.parseDouble(advertistingRevenues);
			}
		}
		res[0] = BasicCalUtil.div(overrallRankSum, overrallRankNum) ;
		res[1] = BasicCalUtil.div(subCategoryRankSum, subCategoryRankNum);
		res[2] = BasicCalUtil.div(totalRatingSum, totalRatingNum);
		res[3] = BasicCalUtil.div(averageRatingSum, averageRatingNum);
		res[4] = noofDownLoadsSum;
		res[5] = BasicCalUtil.div(downloadRevenueSum, downloadRevenueNum);
		res[6] = BasicCalUtil.div(inAppPurcharseRevenueSum, inAppPurcharseRevenueNum);
		res[7] = advertistingRevenuesSum;

		return res;
	}

}
