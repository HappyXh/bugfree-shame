package com.huan.helloworld.util;



/**
 * Created by happy on 9/16/15.
 */
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyHash {
    //record the total number of key-values
    public HashMap<String,Integer> map=new HashMap<>();

    public int size(){
        return map.size();
    }
    public void put( String[] words){
        for(String str: words){
            if(!map.containsKey(str)) {
                map.put(str,map.size());
            }
        }
    }
    public boolean put(String word){
        if(map.containsKey(word)){
           return false;
        }else{
            map.put(word,map.size());
            return true;
        }

    }

    public int index(String str){
        return map.get(str);
    }
    public Object[] index(String[] words){
        ArrayList<Integer> indexs=new ArrayList<Integer>();
        for(String word:words){

            indexs.add(map.get(word));
        }
        return indexs.toArray();
    }

    public HashMap getDictionary(){
        LocalMysql myConn=new LocalMysql();
        String queryStr="select * from dictionary";
        ResultSet rs;
        try {
            rs=myConn.select(queryStr);
            while(rs.next()){
                map.put(rs.getString("word"),rs.getInt("indices"));
            }
            myConn.close();
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
            myConn.close();
            return null;
        }

    }



    public static void main(String[] args) throws IOException {
//        new ReadPDF().pdfToImags(fileStr,"/Users/happy/Downloads/001-060/001 WAL-MART STORES/");
    }


}
