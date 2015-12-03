package com.huan.helloworld.model;

/**
 * Created by happy on 7/22/2015.
 */
public class Story {
    private String uniName;
    private String fileName;
    private String slidesIds;
    private String features;
    private int scan;

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSlidesIds() {
        return slidesIds;
    }

    public void setSlidesIds(String slidesIds) {
        this.slidesIds = slidesIds;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getScan() {
        return scan;
    }

    public void setScan(int scan) {
        this.scan = scan;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    private int favor;
    private int download;



}
