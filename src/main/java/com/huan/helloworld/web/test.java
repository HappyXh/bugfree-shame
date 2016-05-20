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

//        story2json();
//        slide2json();
        String s = "aa,bb,cc,";
        System.out.print(s.substring(0,s.length()-1));

    }
    public static void story2json() throws SQLException, IOException {
        LocalMysql localMysql = new LocalMysql();
        String queryStr = "SELECT * from story";
        ResultSet rs = localMysql.select(queryStr);
        String filePath = "/Users/happy/Downloads/es_ppt_story";
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
    public static void slide2json() throws SQLException, IOException {
        LocalMysql localMysql = new LocalMysql();
        String queryStr = "SELECT * from slides";
        ResultSet rs = localMysql.select(queryStr);
        String filePath = "/Users/happy/Downloads/es_ppt_slides";
        File file = new File(filePath);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(filePath,true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        while(rs.next()){
            bw.newLine();
            bw.write("{ \"index\" : { \"_index\" : \"ppt\", \"_type\" : \"slides\", " +
                    "\"_id\" : \"" + rs.getString("id") + "\" } }");
            bw.newLine();
            bw.write("{ \"filePath\" : \""+rs.getString("filePath")+"\" ," +
                    "\"page\" : \""+rs.getString("page")+"\" ," +
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