package com.cmdi.task;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextParse {
	public static void main(String args[]){
		long startTime = System.currentTimeMillis();
		
		dealData();
		
		long endTime = System.currentTimeMillis();
		System.out.println("共耗时=" + (endTime - startTime) / 60000 + "分钟");
		System.out.println("##end##");
	}
	
	public static void dealData(){
		String contentStr="";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\data\\google_play_data.csv"), "UTF-8"));
			String line=null;
			int count=1;
			reader.readLine();
			for(;(line=reader.readLine())!=null;){     
				String strArray[] = line.split(",");
				if(strArray.length>=16){ 
					System.out.println(strArray.length);
				}
				contentStr = strArray[3];
				if(contentStr.contains("\"")){
//						System.out.println(count++ + ":" +contentStr);
				}
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
