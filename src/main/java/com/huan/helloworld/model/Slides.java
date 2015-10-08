package com.huan.helloworld.model;

/**
 * Created by happy on 7/25/2015.
 */
public class Slides {
    private int id;
    private String filePath;
    private int page;
    private String features;

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    @Override
    public String toString() {
        return String.format(
                "{\"id\":\"%d\",\"filePath\":\"%s\",\"page\":\"%s\",\"features\":\"%s\"}",
                id, filePath, page, features);
    }
}
