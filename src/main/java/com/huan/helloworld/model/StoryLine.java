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

    private class Part{
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
    private class SubPart {
        private String subNumber;
        private String subTopic;
        private int slideId;

        public String getSubNumber() {
            return subNumber;
        }

        public void setSubNumber(String subNumber) {
            this.subNumber = subNumber;
        }

        public String getSubTopic() {
            return subTopic;
        }

        public void setSubTopic(String subTopic) {
            this.subTopic = subTopic;
        }

        public int getSlideId() {
            return slideId;
        }

        public void setSlideId(int slideId) {
            this.slideId = slideId;
        }


    }
}
