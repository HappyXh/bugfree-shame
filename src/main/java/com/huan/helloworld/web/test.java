package com.huan.helloworld.web;

import com.huan.helloworld.model.StoryLine;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by happy on 7/25/2015.
 */
public class test {
    public static void main(String []args) {

        ObjectMapper objectMapper=new ObjectMapper();

        String str="{\"title\":\"problem sovling\",\"parts\":[{\"number\":\"1\",\"topic\":\"Part 1: Identify Challenges/Pain Points/Issues\",\"subParts\":[{\"subNumber\":\"1.1\",\"subTopic\":\"Review positive part of past\",\"slideId\":\"3\"},{\"subNumber\":\"1.2\",\"subTopic\":\"But, here are the problems/issues (data driven)\",\"slideId\":\"4\"},{\"subNumber\":\"1.3\",\"subTopic\":\"Look to the future, бн\",\"slideId\":\"3\"},{\"subNumber\":\"1.4\",\"subTopic\":\"Conclude key questions\",\"slideId\":\"5\"}]},{\"number\":\"2\",\"topic\":\"Part 2: \",\"subParts\":[{\"subNumber\":\"2.1\",\"subTopic\":\"Provide holistic solutions иC Killer Chart\",\"slideId\":\"6\"},{\"subNumber\":\"2.2\",\"subTopic\":\"Solution 1\",\"slideId\":\"7\"},{\"subNumber\":\"2.3\",\"subTopic\":\"Solution 2\",\"slideId\":\"8\"}]},{\"number\":\"3\",\"topic\":\"Part 3: \",\"subParts\":[{\"subNumber\":\"3.1\",\"subTopic\":\"Take away message\",\"slideId\":\"9\"}]}]}";
        try {
            StoryLine storyLine = objectMapper.readValue(str, StoryLine.class);
            System.out.println(storyLine.getTitle());

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
