package com.cmdi.util;

import java.math.BigDecimal;

public class BasicCalUtil {

	public static double div(int v1, int v2) {
		if(v2==0){
			return 0.0;
		}
		else{
			return v1/(double)v2;
		}
	}
	
	public static double div(Long v1, Long v2) {
		if(v2==0){
			return 0.0;
		}
		else{
			return v1/v2;
		}
	}
	
	public static double div(double v1, double v2) {
		if(v2==0){
			return 0.0;
		}
		else{
			return v1/v2;
		}
	}

	public static double getPrecisionData(double data, int precion){
		BigDecimal bigData = new BigDecimal(data);  
		double res = bigData.setScale(precion, BigDecimal.ROUND_HALF_UP).doubleValue();
		return res;
	}
	
}
