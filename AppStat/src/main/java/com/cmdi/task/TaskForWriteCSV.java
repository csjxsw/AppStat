package com.cmdi.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.csvreader.CsvWriter;

public class TaskForWriteCSV {
	
	public static final String [] earfcn = {"F频段","D频段","E频段","全部"};
	public static final String [] covertype = {"室内","室外","全部"};
	public static final String [] scenario = {"党政军机关","党政军宿舍","武警军区","星级酒店","商业中心","写字楼","企事业单位","会展中心","机场","火车站","长途汽车站","码头","高铁","普铁","地铁","高速公路","国道省道","城区道路","郊区道路","航道","休闲娱乐场所","体育场馆","广场公园","风景区","医院","高校","中小学","高层居民区","低层居民区","城中村","别墅群","工业园区","集贸市场","乡镇","村庄","边境小区","沙漠戈壁","山农牧林","近水近海域","公墓","全部"};
	public static final String [] iscore = {"核心城区","非核心城区","全部"};
	public static final String [] isregioncore = {"县城核心区","非县城核心区","全部"};
	
	public static final String [] title = {"编号","earfcn频点号","covertype覆盖类型","scenario场景类型","iscore是否城市核心区域","isregioncore是否县城核心区域"}; 
	
	public static File outFile = new File("E://AppStat.csv");// 输出的CSV文
	public static BufferedWriter writer = null;
	public static CsvWriter cwriter = null;
	
	public static void main(String args[]) throws IOException{
		System.out.println("----------程序运行开始----------------");
		long startTime = System.currentTimeMillis();

//		writer = new BufferedWriter(new FileWriter(outFile));
//		
//		writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
		
		writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "GBK")) ;
		
		cwriter = new CsvWriter(writer, ',');
		cwriter.writeRecord(title, true);
		
		datadeal();
		
		cwriter.close();

		long endTime = System.currentTimeMillis();
		System.out.println("共耗时" + (endTime - startTime) / 60000 + "分钟");
		System.out.println("## the end ##");

	}

	private static void datadeal() throws IOException {
		int num = 1;
		for(int i1=0;i1<earfcn.length;i1++){
			for(int i2=0;i2<covertype.length;i2++){
				for(int i3=0;i3<scenario.length;i3++){
					for(int i4=0;i4<iscore.length;i4++){
						for(int i5=0;i5<isregioncore.length;i5++){
							
							String[] strList = new String[] { String.valueOf(num),
									earfcn[i1],
									covertype[i2],
									scenario[i3],
									iscore[i4],
									isregioncore[i5],
									String.valueOf(0)};
							cwriter.writeRecord(strList, true);
//							cwriter.endRecord();
							cwriter.flush();
							num++;
						}
					}
				}
			}
		}
		
	}
}
