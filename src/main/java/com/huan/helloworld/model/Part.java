package com.huan.helloworld.model;

import java.util.List;

/**
 * Created by happy on 7/25/2015.
 */
public class Part {
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<SubPart> getSubParts() {
        return subParts;
    }

    public void setSubParts(List<SubPart> subParts) {
        this.subParts = subParts;
    }

    private int number;
    private String topic;
    private List<SubPart> subParts;
}
