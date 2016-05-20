package com.huan.helloworld.util;

import org.apache.ibatis.annotations.Update;

import java.sql.*;

/**
 * Created by happy on 9/16/15.
 */
public class LocalMysql {
    static String driver = "com.mysql.jdbc.Driver";
    static String ConnectionString = "jdbc:mysql://localhost:3307/helloworld";
//    static String ConnectionString = "jdbc:mysql://localhost:3306/helloworld";
    static String password = "";
//    static String password = "poinThinker";
    static String username = "root";

    private Connection conn;

    public LocalMysql() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection (ConnectionString,username,password);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public final Connection getConnection()
    {
        return conn;
    }
    public void insert(String queryStr) throws SQLException {
        this.conn.createStatement().executeUpdate(queryStr);
    }
    public ResultSet select(String queryStr) throws SQLException {
        return this.conn.prepareStatement(queryStr).executeQuery();
    }

    public void update(String queryStr) throws SQLException {
        this.conn.createStatement().executeUpdate(queryStr);
    }
    public void delete(String queryStr) throws SQLException {
        this.conn.createStatement().executeUpdate(queryStr);
    }
    public int getSlideIDByFileAndPage(String fileName, int page) throws SQLException {
        String queryStr="Select id from slides where filePath=\"" + fileName +
                "\" and page=" + page + ";";
        ResultSet rs=this.conn.prepareStatement(queryStr).executeQuery();
        if(rs.next())
            return rs.getInt(1);
        else
            return 0;
    }
    public String getInverseIdsByWord(String word) throws SQLException {
        String queryStr="Select ids from dictionary where word=\"" + word + "\";";
        ResultSet rs=this.conn.prepareStatement(queryStr).executeQuery();
        if(rs.next())
            return rs.getString(1);
        else
            return null;

    }
    public String[] getSlidesIdsByWord(String word) throws SQLException {
        String queryStr="SELECT ids from dictionary where word=\"" + word + "\";";
        ResultSet rs=this.conn.prepareStatement(queryStr).executeQuery();
        if(rs.next())
            return rs.getString(1).split(",");
        else
            return null;
    }
    public String getFileById(int id) throws SQLException {
        String queryStr = "SELECT filePath from slides where id=" + id + ";";
        ResultSet rs=this.conn.prepareStatement(queryStr).executeQuery();
        if(rs.next())
            return rs.getString(1);
        else
            return null;
    }
    public int getPageById(int id) throws SQLException {
        String queryStr = "SELECT page from slides where id=" + id + ";";
        ResultSet rs=this.conn.prepareStatement(queryStr).executeQuery();
        if(rs.next())
            return Integer.parseInt(rs.getString(1));
        else
            return 1;
    }
}
