package com.huan.helloworld.model;

import java.util.List;

/**
 * Created by happy on 7/23/2015.
 */
public class StoryLine {
    private String title;
    private List<Part> parts;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

}
