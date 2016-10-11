package com.cmdi.task;

import java.io.BufferedReader;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
public class DbStoreHelper {  
  
    private String insert_sql;  
    private String charset;  
    private boolean debug;  
  
    private String connectStr;  
    private String username;  
    private String password;  
  
    public DbStoreHelper() {  
        connectStr = "jdbc:mysql://localhost:3306/dataservice";  
        // connectStr += "?useServerPrepStmts=false&rewriteBatchedStatements=true";  
        insert_sql = "INSERT INTO apptable (Date,Country,App ID,App Name,Category,Subcategory,Overall rank,Category Rank,Subcategory Rank,Total # Ratings,Average Ratings,No of Downloads,Download revenue,In app purchases revenue,Advertising revenues,) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
        charset = "utf8";  
        debug = true;  
        username = "root";  
        password = "123456";  
    }  
  
    public void storeToDb(String srcFile) throws IOException {  
        BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), charset));  
        try {  
            doStore(bfr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            bfr.close();  
        }  
    }  
  
    private void doStore(BufferedReader bfr) throws ClassNotFoundException, SQLException, IOException {  
        Class.forName("com.mysql.jdbc.Driver");  
        Connection conn = DriverManager.getConnection(connectStr, username,password);  
        conn.setAutoCommit(false); // 设置手动提交  
        int count = 0;  
        PreparedStatement psts = conn.prepareStatement(insert_sql);  
        String line = null;  
        while (null != (line = bfr.readLine())) {  
            String[] infos = line.split(";");  
            if (infos.length < 5)   continue;  
            if (debug) {  
                System.out.println(line);  
            }  
            psts.setLong(1, Long.valueOf(infos[0]));  
            psts.setLong(2, Long.valueOf(infos[1]));  
            psts.setString(3, infos[2]);  
            psts.setString(4, infos[3]);  
            psts.setString(5, infos[4]);  
            psts.addBatch();          // 加入批量处理  
            count++;              
        }  
        psts.executeBatch(); // 执行批量处理  
        conn.commit();  // 提交  
        System.out.println("All down : " + count);  
        conn.close();  
    }  
  
}   