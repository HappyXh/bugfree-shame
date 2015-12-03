package com.huan.helloworld.util;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by happy on 11/20/15.
 */
public class ReadPPT {
    private static String slidesPath = "http://7xoiwj.com1.z0.glb.clouddn.com/";
//    private static String pptPath = "src/main/webapp/attachment/";
    private static String pptPath = "/var/lib/tomcat7/webapps/ROOT/attachment/";

    private static String fileStr = "/Users/happy/Documents/001-020/";
//    private static String fileStr = "/Users/happy/Documents/test/";
//    private static String tmpFile = "src/main/webapp/tmpFile/";
    private static String tmpFile = "/var/lib/tomcat7/webapps/ROOT/tmpFile/";

    public static void ppt2Images(XMLSlideShow src, String fileName) throws IOException {
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        Dimension pgsize = src.getPageSize();
        BufferedImage img = new BufferedImage(pgsize.width,
                pgsize.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();
        graphics.setPaint(Color.white);
        graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,
                pgsize.height));
        int count =1;
        for(XSLFSlide slide : src.getSlides()) {
            slide.draw(graphics);

            FileOutputStream out = new FileOutputStream("src/main/webapp/attachment/tmp.jpg");
            javax.imageio.ImageIO.write(img, "jpg", out);
            out.close();
            File tmpImage = new File("src/main/webapp/attachment/tmp.jpg");
            Qiniu.uploadFile(tmpImage ,fileName + "-" + count + ".jpg");
            tmpImage.delete();
            count += 1;
        }
    }

    public static String extactText(XSLFSlide slide) throws IOException {
        String text="";
        for(XSLFShape shape : slide) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape txShape = (XSLFTextShape) shape;
                text += txShape.getText();
            }
        }
        text = text.replaceAll("[^a-zA-Z\\s]", " ");
        text = text.replaceAll("\\s+", " ").toLowerCase();
        return text;
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    public static void uploadSlide2Sql(String filePath, int page, String features,
                                       LocalMysql myConn) throws SQLException {
        String queryStr = "INSERT INTO slides(filePath,page,features) VALUES(\""
                + filePath + "\"," + page + ",\"" + features + "\") ";
        myConn.insert(queryStr);
    }
    public static void uploadPPT2Sql(String uniName, String fileName, String slideIds,
                                     String features, LocalMysql myConn) throws SQLException {
        String queryStr = "INSERT INTO story(uniName,fileName,slidesIds,features) VALUES(\""
                + uniName + "\",\"" + fileName + "\",\"" + slideIds + "\",\"" + features + "\") ";
        System.out.println(queryStr);
        myConn.insert(queryStr);
    }

    public static void insertIndex(String word, int indices, int id, LocalMysql myConn){
        String queryStr = "INSERT INTO dictionary(word, indices, ids) values(\""
                + word + "\"," + indices + ",\"" + id +"\")";
        try {
            myConn.insert(queryStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateIndex(String word, int id, LocalMysql myConn) throws SQLException {
        String ids = myConn.getInverseIdsByWord(word);
        if(ids != null){
            if(ids.contains(id + "")) return;
            ids += "," + id;
            String queryStr="UPDATE dictionary set ids=\"" + ids + "\" where word=\"" + word + "\"";
            myConn.update(queryStr);
        }
    }

    public static void upload() throws SQLException, IOException {
        String key;
        String content;
        XMLSlideShow src = null;

        //获取当前词典
        MyHash myHash=new MyHash();
        myHash.map.putAll(myHash.getDictionary());

        LocalMysql myConn=new LocalMysql();
        //获取文档总目录
        File file = new File(fileStr);
        if (file.isDirectory()) {
            //遍历公司
            String[] fileList = file.list();
            for(String subFile : fileList){
                //获取公司文档目录
                File file1 = new File(fileStr+subFile);
                if(file1.isDirectory()){
                    //遍历公司文件
                    String[] fileList1 = file1.list();
                    for(String subFile1 : fileList1){
                        //获取文件地址
                        if(!subFile1.endsWith(".pptx")){
                            continue;
                        }
                        key = UUID.randomUUID() + ".pptx";
                        try {
                            copyFile(file1 + "/" + subFile1, pptPath + key);
                            FileInputStream is = new FileInputStream(file1 + "/" + subFile1);
                            src = new XMLSlideShow(is);
                            is.close();
                        }catch (NoClassDefFoundError e){
                            continue;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        XSLFSlide[] slides = src.getSlides();
                        int pageNum=slides.length;
                        //存储slides的Id
                        java.util.List<Integer> slideIds =  new ArrayList<>();
                        for(int i = 0; i < pageNum; i++){
                            content=extactText(slides[i]);
                            String[] words=content.split(" ");
                            java.util.List<String> str_list = new ArrayList<String>();
                            for (int j=0; j<words.length; j++) {
                                if(!str_list.contains(words[j])) {
                                    str_list.add(words[j]);
                                }
                            }
                            if(str_list.toString() == "[]"){
                                str_list.add("");
                            }

                            //将本页Slide上传到数据库
                            uploadSlide2Sql(key, i + 1,
                                    str_list.toString().substring(1, str_list.toString().length() - 1),
                                    myConn);

                            //更新词典索引
                            int slideId = myConn.getSlideIDByFileAndPage(key, i-1);
                            slideIds.add(slideId);
                            for(String tmp: str_list){
                                if(myHash.put(tmp)) {
                                    insertIndex(tmp, myHash.map.size(),slideId,myConn);
                                }
                                else updateIndex(tmp,slideId,myConn);
                            }
                        }
                        uploadPPT2Sql(key, subFile1.substring(0, subFile1.lastIndexOf(".")),
                                slideIds.toString().substring(1, slideIds.toString().length() - 1),
                                "", myConn);
                        ppt2Images(src, key);
                    }
                }
            }
        }
        myConn.close();
    }

    public static void createPPT(List<Integer> slides, LocalMysql myConn, String fileName)
            throws SQLException, IOException {
        int slides_length = slides.size();
        int[] slidesId = new int[slides_length];
        String[] inputs = new String[slides_length];
        for(int i =0;i<slides_length;i++){
            inputs[i] = myConn.getFileById(slides.get(i));
            slidesId[i] = myConn.getPageById(slides.get(i));
        }
        int i =0;
        XMLSlideShow ppt = new XMLSlideShow();
        for(String arg : inputs){
            FileInputStream is = new FileInputStream(pptPath+arg);
            XMLSlideShow src = new XMLSlideShow(is);
            is.close();
            ppt.createSlide().importContent(src.getSlides()[slidesId[i] - 1]);
            i += 1;
        }

        FileOutputStream out = new FileOutputStream(tmpFile + fileName);
        ppt.write(out);
        out.close();
        ppt.close();
    }

    public static void main(String args[]) throws IOException, SQLException {
//        upload();
        System.out.println(System.getProperty("user.dir") );
    }

}
