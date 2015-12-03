package com.huan.helloworld.web;



import com.huan.helloworld.util.LocalMysql;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by happy on 7/25/2015.
 */



public class test {

    public static void main(String[] args) throws IOException, SQLException {
//        LocalMysql localMysql = new LocalMysql();
//        String queryStr = "SELECT * from story";
//        ResultSet rs = localMysql.select(queryStr);
//        while(rs.next()){
//            String[] slidesIds = rs.getString("slidesIds").replaceAll("\\s+", "").split(",");
//            String uniName = rs.getString("uniName");
//            String story_feature = "";
//            for(String str : slidesIds){
//                int id = Integer.parseInt(str);
//                if(id != 0){
//                    String queryStr1 = "select * from slides where id =" + id;
//                    ResultSet rs1 = localMysql.select(queryStr1);
//                    if(rs1.next()) {
//                        story_feature = story_feature + "," + rs1.getString("features");
//                    }
//                }
//            }
//            String queryStr2 = "UPDATE story set features=\"" + story_feature
//                    + "\" where uniName=\"" + uniName + "\"";
//            localMysql.update(queryStr2);
//        }
        sql2json();

    }
    public static void sql2json() throws SQLException, IOException {
        LocalMysql localMysql = new LocalMysql();
        String queryStr = "SELECT * from story";
        ResultSet rs = localMysql.select(queryStr);
        String filePath = "src/main/resources/sql2json";
        File file = new File(filePath);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(filePath,true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        while(rs.next()){
            bw.newLine();
            bw.write("{ \"index\" : { \"_index\" : \"ppt\", \"_type\" : \"story\", " +
                    "\"_id\" : \"" + rs.getString("uniName") + "\" } }");
            bw.newLine();
            bw.write("{ \"fileName\" : \""+rs.getString("fileName")+"\" ," +
                    "\"slidesIds\" : \""+rs.getString("slidesIds")+"\" ," +
                    "\"features\" : \""+rs.getString("features")+"\" ," +
                    "\"scan\" : \""+rs.getString("scan")+"\" ," +
                    "\"favor\" : \""+rs.getString("favor")+"\" ," +
                    "\"download\" : \""+rs.getString("download")+"\"}" );
        }
        fileWriter.flush();
        bw.close();
        fileWriter.close();
    }

}