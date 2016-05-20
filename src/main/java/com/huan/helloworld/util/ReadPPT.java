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
    private static String pptPath = "/Users/happy/Documents/project/IntellijProject/attachment/";
//    private static String pptPath = "/var/lib/tomcat7/webapps/ROOT/attachment/";
    private static String tmpFile = "src/main/webapp/tmpFile/";
//    private static String tmpFile = "/var/lib/tomcat7/webapps/ROOT/tmpFile/";
    private static String fileStr = "/Users/happy/Documents/test/";

//    private static String fileStr = "/Users/happy/Documents/001-020/";



    public static HashSet<String> stopWords;
    public static Stemmer stemmer = new Stemmer();
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

            FileOutputStream out = new FileOutputStream(pptPath+"tmp.jpg");
            javax.imageio.ImageIO.write(img, "jpg", out);
            out.close();
            File tmpImage = new File(pptPath+"tmp.jpg");
            Qiniu.uploadFile(tmpImage ,fileName + "-" + count + ".jpg");
            tmpImage.delete();
            count += 1;
        }
    }

    public static List<String> extactText(XSLFSlide slide) throws IOException {
        String text="";
        for(XSLFShape shape : slide) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape txShape = (XSLFTextShape) shape;
                text += txShape.getText();
            }
        }
        text = text.replaceAll("[^a-zA-Z'\\s]", " ");
        text = text.replaceAll("\\s+", " ").toLowerCase();
        String[] contents = text.split(" ");
        List<String> words = new ArrayList<>();
        for(int i =0; i<contents.length; i++){
            if(!stopWords.contains(contents[i])){
                words.add(stemmer.stem(contents[i]));
            }
        }
        return words;
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
    public static void uploadDefault() throws IOException, SQLException {
        LocalMysql myConn=new LocalMysql();
        String queryStr = "INSERT INTO slides(id,filePath,page) VALUES(1,\"default.pptx\",1) ";
//        myConn.insert(queryStr);
        queryStr = "INSERT INTO story(uniName,fileName,slidesIDs) VALUES(" +
                "\"default.pptx\",\"default\",\"1\")";
        myConn.insert(queryStr);
//        File tmpImage = new File(pptPath+"default-1.jpg");
//        Qiniu.uploadFile(tmpImage, "default-1.jpg");
    }

    public static void upload() throws SQLException, IOException {
        String key;
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
                        String feature = "";
                        for(int i = 0; i < pageNum; i++){

                            List<String> words=extactText(slides[i]);

                            //将本页Slide上传到数据库
                            uploadSlide2Sql(key, i + 1,
                                    words.toString().substring(1, words.toString().length() - 1),
                                    myConn);
                            feature = feature + "," +
                                    words.toString().substring(1, words.toString().length() - 1);

                            //更新词典索引
                            int slideId = myConn.getSlideIDByFileAndPage(key, i-1);
                            slideIds.add(slideId);
                            java.util.List<String> str_list = new ArrayList<String>();
                            for (int j=0; j<words.size(); j++) {
                                if(!str_list.contains(words.get(j))) {
                                    str_list.add(words.get(j));
                                }
                            }

                            for(String tmp: str_list){
                                if(myHash.put(tmp)) {
                                    insertIndex(tmp, myHash.map.size(),slideId,myConn);
                                }
                                else updateIndex(tmp,slideId,myConn);
                            }
                        }
                        uploadPPT2Sql(key, subFile1.substring(0, subFile1.lastIndexOf(".")),
                                slideIds.toString().substring(1, slideIds.toString().length() - 1),
                                feature, myConn);
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
//        stopWords = new HashSet<>();
//        String tempString;
//        File file = new File("src/main/resources/stopwords.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        while((tempString = reader.readLine()) != null){
//            stopWords.add(tempString);
//        }
//        upload();
//        System.out.println(System.getProperty("user.dir") );
        uploadDefault();
    }

}
