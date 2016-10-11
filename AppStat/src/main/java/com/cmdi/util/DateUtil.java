package com.cmdi.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
	
	public static final String DefaultFormat = "yyyyMMddHHmm";
	
	public static final String[] hours ={
		/*"00","01","02","03","04","05","06","07","08","09","10","11",
		"12","13","14","15","16","17","18","19","20","21","22","23"*/
		"12"/*,"01","02","03","04","05","06","07","08"*/
	};
	
	public static final String[] days ={
		/*"25","26","27","28","29"*/"06"
	};
	
	public static final String[] years ={
		"2014","2015"
	};
	
	public static final String[] months ={
		"01","02","03","04","05","06","07","08","09","10","11","12"
	};
	
	private static final List<String> checkMonth= new ArrayList<String>(){{
		add("05");
		add("07");
		add("10");
		add("12");
	}};
	
	public static final String getDate(String dateFormat){
		Date now = new Date();
		SimpleDateFormat date = new SimpleDateFormat(dateFormat);
		return date.format(now);
	}
	
	public static final String getHour(){
		return getDate("hh");
	}
	
	public static final String getToday(){
		return getDate("dd");
	}
	
	public static final String getMonth(){
		return getDate("MM");
	}
	
	public static final String getYear(){
		return getDate("yyyy");
	}

	public static String getTempHour() {
		return getToday()+getHour();
	}

	public static final String getYestoday(){
		if(1 == Integer.parseInt(getToday())){
			if(checkMonth.contains(getDate("MM"))){
				return "30";
			} else if ("03" == getMonth()){
				int year = Integer.parseInt(getYear());
		        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) 
		        	return "29"; 
		        else 
		        	return "28";
			} else return "31";
		} else {
			int yes = Integer.parseInt(getToday()) - 1;
			if(yes < 10)
				return "0" + yes;
			return String.valueOf(yes);
		}
	}
	
	public static String getLastHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) -1);
		
		String day = getToday(calendar.getTime());
		String hour = getHour(calendar.getTime());
		
		System.out.println("一小时前的时间：" + day+hour);
		return day+hour;
	}
	
	public static String getLastDay() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        String year = getYear(calendar.getTime());
        String month = getMonth(calendar.getTime());
        String day = getToday(calendar.getTime());
        
//        System.out.println("一天前的时间：" + year+month+day);
        return year+month+day;//20160809
        
    }  
	
	public static String getLastDay1() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        String year = getYear1(calendar.getTime());
        String month = getMonth(calendar.getTime());
        String day = getToday(calendar.getTime());
        return year+month+day;
    }  
	
	public static final String getDate(Date time, String dateFormat){
		SimpleDateFormat date = new SimpleDateFormat(dateFormat);
		return date.format(time);
	}
	
	public static final String getYear(Date time){
		return getDate(time, "yyyy");
	}
	
	public static final String getYear1(Date time){
		return getDate(time, "yy");
	}
	
	public static final String getMonth(Date time){
		return getDate(time, "MM");
	}
	
	public static final String getToday(Date time){
		return getDate(time, "dd");
	}
	
	public static final String getHour(Date time){
		return getDate(time, "HH");
	}
	
	public static void main(String args[]){
		System.out.println(getLastDay1());
	}
}
