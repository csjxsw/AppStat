package com.cmdi.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.csvreader.CsvReader;
//import com.csvreader.CsvWriter;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException; 
import java.io.InputStreamReader;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class StringRegex {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException { 
    	
        String connectStr = "jdbc:mysql://localhost:3306/dataservice";  
        // connectStr += "?useServerPrepStmts=false&rewriteBatchedStatements=true";  
        String insert_sql = "INSERT INTO apptable (Date,Country,AppID,AppName,Category,Subcategory,OverallRank,CategoryRank,SubcategoryRank,TotalRatings,AverageRatings,NoofDownloads,Downloadrevenue,Inapppurchasesrevenue,Advertisingrevenues) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
        String charset = "utf8";  
        String username = "root";  
        String password = "123456";     
    	
        String [] str = {"省","市","区","街","路","里","幢","村","室","园","苑","巷","号"};
        String inString = "";
        String tmpString = "";
        File inFile = new File("E:\\data\\google_play_data.csv"); // 读取的CSV文件
        File outFile = new File("E://outtest.csv");//输出的CSV文
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            CsvReader creader = new CsvReader(reader, ',');
            CsvWriter cwriter = new CsvWriter(writer,',');
            
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(connectStr, username,password);  
            conn.setAutoCommit(false); // 设置手动提交  
            int count = 0;  
            PreparedStatement psts = conn.prepareStatement(insert_sql);  
            
            creader.readRecord();
            while(creader.readRecord()){
                String tempString []= creader.getValues();//.getRawRecord();//读取一行数据
                
                String date = tempString[0];
                String country = tempString[1];
                String appID = tempString[2];
                String appName = tempString[3];
                
                String overall_rank = tempString[6];
                String subcategory_rank = tempString[8];
                String total_rating = tempString[9];
                String average_rating = tempString[10];
                String numOfDownLoads = tempString[11];
                String downLoadRevenue = tempString[12];
                String inApp_purchase_revenue = tempString[13];
                String advertistingRevenues = tempString[14];
                
                System.out.println(tempString[0]+"  "+tempString[1]+"  "+tempString[2]+"  "+tempString[3]+"  "+tempString[4]);
                
                psts.setString(1, tempString[0]);  
                psts.setString(2, tempString[1]);  
                psts.setString(3, tempString[2]);  
                psts.setString(4, tempString[3]);  
                psts.setString(5, tempString[4]);  
                psts.setString(6, tempString[5]);  
                psts.setString(7, tempString[6]);  
                psts.setString(8, tempString[7]);  
                psts.setString(9, tempString[8]);  
                psts.setString(10, tempString[9]);  
                psts.setString(11, tempString[10]);  
                psts.setString(12, tempString[11]);  
                psts.setString(13, tempString[12]);  
                psts.setString(14, tempString[13]);  
                psts.setString(15, tempString[14]);  
                psts.addBatch();         
                count++;      
                
                if(count ==10000){
                    psts.executeBatch(); // 执行批量处理  
                    conn.commit();  // 提交 
                    count=0;
                }
                
//                System.out.println(tempString[0]+"  "+tempString[1]+"  "+tempString[2]+"  "+tempString[3]);
//                Thread.sleep(500);
//                for(int i = 0;i < str.length;i++){
////		                    tmpString = inString.replace(str[i], "," + str[i] + ",");
//                	tmpString = inString;
//                    System.out.println(tmpString);
//                }
//                第一个参数表示要写入的字符串数组，每一个元素占一个单元格，第二个参数为true时表示写完数据后自动换行
            cwriter.writeRecord(inString.split(","), true);
            //注意，此时再用cwriter.write(inString)方法写入数据将会看到只往第一个单元格写入了数据，“，”没起到调到下一个单元格的作用
            //如果用cwriter.write(String str)方法来写数据，则要用cwriter.endRecord()方法来实现换行
            cwriter.endRecord();//换行
            cwriter.flush();//刷新数据
            }  
            conn.close(); 
            creader.close();
            cwriter.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}