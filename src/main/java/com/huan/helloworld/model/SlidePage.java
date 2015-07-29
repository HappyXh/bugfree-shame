package com.huan.helloworld.model;

/**
 * Created by happy on 7/25/2015.
 */
public class SlidePage {
    private int id;
    private String filePath;
    private int page;

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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

    private String feature;

    @Override
    public String toString() {
        return String.format(
                "{\"id\":\"%d\",\"filePath\":\"%s\",\"page\":\"%s\"}",
                id, filePath, page);
    }
}
