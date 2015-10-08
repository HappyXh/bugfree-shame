package com.huan.helloworld.util;

import com.itextpdf.text.*;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by happy on 9/16/15.
 */
public class ReadPDF {

    private static String slidesPath = "http://7xme1x.com1.z0.glb.clouddn.com/";
    private static String pptPath = "/Users/happy/Documents/ppt/";
    private static String fileStr = "/Users/happy/Documents/001-020/";
//    private static String fileStr = "/Users/happy/Documents/test/";
    private String tmpFile;

    public ReadPDF(){
        this.tmpFile="/Users/happy/Documents/createSample.pdf";
    }
    public ReadPDF(String tmpFile){
        this.tmpFile=tmpFile;
    }
    public PdfReader getReader(String fileName) throws IOException {
        return new PdfReader(fileName);
    }

    public static String extractFileType(String fileName) {
        if (fileName == null || fileName.indexOf(".") == -1) return "";
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }

    public static void insertIndex(String word, int indices, int id, LocalMysql myConn){
        String queryStr = "INSERT INTO dictionary(word, indices, ids) values(\"" + word + "\"," + indices + ",\"" + id +"\")";
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

    public void copyFile(String oldPath, String newPath) {
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

    public String readPage(PdfReader reader, int i) throws IOException {
        String content;
        content = PdfTextExtractor.getTextFromPage(reader, i); //读取第i页的文档内容
        content = content.replaceAll("[^a-zA-Z\\s]", " ");
        content = content.replaceAll("\\s+", " ").toLowerCase();
        return content;
    }

    public void uploadSlideToSql(String filePath, int page, String features, LocalMysql myConn) throws SQLException {
        String queryStr = "INSERT INTO slides(filePath,page,features) VALUES(\"" + filePath + "\"," + page + ",\"" + features + "\") ";
        myConn.insert(queryStr);
//        ResultSet rs = ps.executeQuery();
    }

    public void pdfToImages(String filePath, String savePath) throws IOException {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1,
                filePath.length());
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        File file = null;
        InputStream in = null;
        java.util.List pages = null;
        int buff = 1024 * 1024;
        PDDocument pdDocument = null;
        try {
            file = new File(filePath);
            if (file.exists()) {
                in = new BufferedInputStream(new FileInputStream(filePath),
                        buff);
                PDFParser parser = new PDFParser(in);
                parser.parse();
                pdDocument = parser.getPDDocument();
                pages = pdDocument.getDocumentCatalog().getAllPages();
                for (int i = 0; i < pages.size(); i++) {
                    PDPage page = (PDPage) pages.get(i);
                    BufferedImage img_temp = page.convertToImage();
                    Iterator iterator = ImageIO
                            .getImageWritersBySuffix("jpg");
                    ImageWriter writer = (ImageWriter) iterator.next();
                    ImageOutputStream imageOut = ImageIO
                            .createImageOutputStream(new FileOutputStream(
                                    new File(savePath + "/" + fileName + "-"
                                            + i + ".jpg")));
                    writer.setOutput(imageOut);
                    writer.write(new IIOImage(img_temp, null, null));
                    //upload image to Qiniu
                    File tmpImage = new File(savePath + "/" + fileName + "-" + i + ".jpg");
                    Qiniu.uploadFile(tmpImage ,fileName + "-" + i + ".jpg");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pdDocument != null) {
                pdDocument.close();
            }

        }
    }
    public void upLoad() throws IOException, SQLException {
        String fileName;
        String key;
        String content;
        int slideId;
//        List<String> newWords=new ArrayList<>();
        PdfReader reader;
        ReadPDF myReadPDF=new ReadPDF();

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
                        if(!subFile1.endsWith(".pdf")){
                            continue;
                        }
                        key = UUID.randomUUID() + extractFileType(subFile1);
                        fileName = pptPath + key;
                        try {
                            myReadPDF.copyFile(file1 + "/" + subFile1, fileName);
                            reader = myReadPDF.getReader(fileName);
                        }catch(InvalidPdfException e){
                            continue;
                        }catch (NoClassDefFoundError e){
                            continue;
                        }
                        int pageNum=reader.getNumberOfPages();
                        for(int i = 1; i <= pageNum; i++){
                            content=myReadPDF.readPage(reader,i);
                            String[] words=content.split(" ");
                            List<String> str_list = new ArrayList<String>();
                            for (int j=0; j<words.length; j++) {
                                if(!str_list.contains(words[j])) {
                                    str_list.add(words[j]);
                                }
                            }
                            if(str_list.toString() == "[]"){
                                str_list.add("");
                            }

                            //将本页Slide上传到数据库
                            myReadPDF.uploadSlideToSql(key,i-1,str_list.toString().substring(1,str_list.toString().length()-1),myConn);
                            //更新词典索引

                            slideId = myConn.getSlideIDByFileAndPage(key, i-1);
                            for(String tmp: str_list){
                                if(myHash.put(tmp)) {
//                                    newWords.add(tmp);
                                    insertIndex(tmp, myHash.map.size(),slideId,myConn);
                                }
                                else updateIndex(tmp,slideId,myConn);
                            }
                        }
                        myReadPDF.pdfToImages(fileName, slidesPath);
                    }
                }
            }
        }
//        int count=newWords.size();
//        String queryStr;
//        for(int i=0;i<count;i++){
//            try {
//                queryStr="INSERT INTO dictionary(word,indices) values(\""+newWords.get(i)+"\","+myHash.map.get(newWords.get(i))+")";
//                myConn.insert(queryStr);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        myConn.close();
    }

    public static String getJPGPath(int slidesId, LocalMysql myConn){
        ResultSet resultSet;
        String pdfName = "";
        String jpgPath = "";
        int page;

        String queryStr = "SELECT filePath, page FROM slides WHERE id = " + slidesId;
        try {
            resultSet = myConn.select(queryStr);
            if(resultSet.next())
                pdfName = resultSet.getString(1);
            page = resultSet.getInt(2);
            jpgPath = slidesPath + pdfName.substring(0, pdfName.lastIndexOf('.')) + "-" + page + ".jpg";
            return jpgPath;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getPercent(float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 550/ w * 100;
        p = Math.round(p2);
        return p;
    }

    public void createPDF(List<Integer> slides, LocalMysql myConn) throws FileNotFoundException{
        Rectangle rect = new Rectangle(PageSize.B5.rotate());
        Document document = new Document(rect);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(tmpFile));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.open();

        String jpgPath = "";
        for (int id: slides){
            jpgPath = ReadPDF.getJPGPath(id, myConn);
            document.newPage();
            try {
                Image png1 = Image.getInstance(jpgPath);
                int percent = getPercent(png1.getWidth());
                png1.setAlignment(Image.MIDDLE);
                png1.scalePercent(percent+3);
                document.add(png1);
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        document.close();
    }



    public static void main(String []args) throws SQLException {


        ReadPDF reader= new ReadPDF();
        try {
            reader.upLoad();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        LocalMysql myConn=new LocalMysql();
//        ResultSet resultSet;
//        List<Integer> slides = new ArrayList<>();
//        slides.add(5300);
//        slides.add(6801);
//
//        try {
//            ReadPDF.createPDF(slides, myConn);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        myConn.close();

//        String[] s = myConn.getSlidesIdsByWord("us");
//        for(String str : s) System.out.println(str);


    }
}