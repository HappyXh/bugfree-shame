package com.huan.helloworld.web;


import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.hslf.model.TextPainter;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by happy on 7/25/2015.
 */
public class test {
    public static void main(String []args) {

        String str1="\"name\"";
        String str2=str1.replaceAll("\"","&nbsp;");
        System.out.println(str2);
//        try {
//
//            XMLSlideShow ppt = new XMLSlideShow();
//            String[] inputs = {"D:/source.pptx"};
//            for(String arg : inputs){
//                FileInputStream is = new FileInputStream(arg);
//                XMLSlideShow src = new XMLSlideShow(is);
//                is.close();
//
//                for(XSLFSlide srcSlide : src.getSlides()){
//                    ppt.createSlide().importContent(srcSlide);
//
//                }
//            }
//
//            FileOutputStream out = new FileOutputStream("D:/merged.pptx");
//            ppt.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
