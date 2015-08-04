package com.huan.helloworld.web;

import com.huan.helloworld.model.SlidePage;
import com.huan.helloworld.model.Story;
import com.huan.helloworld.model.StoryLine;
import com.huan.helloworld.model.SubPart;
import com.huan.helloworld.service.SlidePageService;
import com.huan.helloworld.service.StoryService;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Shape;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by happy on 7/25/2015.
 */
@Controller
@RequestMapping("/slide")
public class SlideController {
    @Autowired
    SlidePageService slidePageService;
    @Autowired
    StoryService storyService;

    private static final String OUTPUT_PATH="resource/output";
    @RequestMapping(value="/createPPT",method = RequestMethod.POST)
    public String createPPT( HttpServletRequest request,ModelMap map)  {

        String slidesStr = request.getParameter("slides");
        String[] slidesArr=slidesStr.substring(1).split(",");


        try {

            XMLSlideShow ppt = new XMLSlideShow();

            for(String arg : slidesArr){
                SlidePage slidePage =slidePageService.findById(Integer.parseInt(arg));
                FileInputStream is = new FileInputStream("C:/Users/happy/Documents/git/bugfree-shame/src/main/webapp/"+slidePage.getFilePath());
                XMLSlideShow src = new XMLSlideShow(is);
                is.close();

                ppt.createSlide().importContent(src.getSlides()[slidePage.getPage()]);

            }

            FileOutputStream out = new FileOutputStream("D:/merged.pptx");
            ppt.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "slide/success";

    }

    @RequestMapping(value="{id}/{index}/select",method = RequestMethod.POST)
    public @ResponseBody String[] chooseAjax( @PathVariable("id") int id,@PathVariable("index") int index, HttpServletRequest request,ModelMap map)  {

        int MAX_NUM=slidePageService.fiinAll().size();
        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int i;
        while(list.size() < 6){
            i = r.nextInt(MAX_NUM)%MAX_NUM+ 1;
            if(!list.contains(i)){
                list.add(i);
            }
        }
        String[] str=new String[4];
        str[1]="[";
        for(i=0;i<list.size()-1;i++){
            str[1]+=slidePageService.findById(list.get(i)).toString()+",";
        }
        str[1]=str[1].substring(0,str[1].length()-1)+"]";
        str[0]="1";

        return str;
    }

    @RequestMapping(value="{id}/{index}/choose",method = RequestMethod.POST)
    public String choose( @PathVariable("id") int id,@PathVariable("index") int index, HttpServletRequest request,ModelMap map)  {
        String slideIdStr = request.getParameter("slideIdStr");
        Story story=storyService.findById(id);

        ObjectMapper objectMapper = new ObjectMapper();
        StoryLine storyLine = null;
        try {
            storyLine = objectMapper.readValue(story.getStoryLine(), StoryLine.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.addAttribute("title",storyLine.getTitle());
        map.addAttribute("parts",storyLine.getParts());
        map.addAttribute("id",id);
        map.addAttribute("index",index);
        map.addAttribute("slideIdStr",slideIdStr);

        int MAX_NUM=slidePageService.fiinAll().size();
        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int i;
        while(list.size() < 6){
            i = r.nextInt(MAX_NUM)%MAX_NUM+ 1;
            if(!list.contains(i)){
                list.add(i);
            }
        }
        ArrayList<SlidePage> slides = new ArrayList();
        for(i=0;i<list.size()-1;i++){
            slides.add(slidePageService.findById(list.get(i)));
        }
        map.addAttribute("slides",slides);

        return "slide/selectSlide";

    }

    @RequestMapping(value="/{id}/reSelect",method = RequestMethod.POST)
    public String getStoryLine(@PathVariable("id") int id, ModelMap map,HttpServletRequest request) throws IOException {
        String slideIdStr = request.getParameter("slideIdStr");
        String[] slideArr=slideIdStr.split(",");
        Story story = storyService.findById(id);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StoryLine storyLine = objectMapper.readValue(story.getStoryLine(), StoryLine.class);
            map.addAttribute("id", id);
            map.addAttribute("title", storyLine.getTitle());
            map.addAttribute("parts", storyLine.getParts());
            ArrayList<SlidePage> slides = new ArrayList();

            for (int i = 0; i < slideArr.length; i++) {
                slides.add(slidePageService.findById(Integer.parseInt(slideArr[i])));
            }
            map.addAttribute("slides", slides);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "slide/showSlide";
    }

    @RequestMapping(value="/{id}/select",method = RequestMethod.POST)
    public String getStoryLine(@PathVariable("id") int id, ModelMap map) throws IOException {
        Story story=storyService.findById(id);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StoryLine storyLine = objectMapper.readValue(story.getStoryLine(), StoryLine.class);
            map.addAttribute("id",id);
            map.addAttribute("title", storyLine.getTitle());
            map.addAttribute("parts",storyLine.getParts());
            ArrayList<SlidePage> slides = new ArrayList();

            for(int i=0;i<storyLine.getParts().size();i++){
                List<SubPart> subparts=storyLine.getParts().get(i).getSubParts();
                for(int j=0;j<subparts.size();j++){
                    slides.add(slidePageService.findById(subparts.get(j).getSlideId()));
                }
            }
            map.addAttribute("slides",slides);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "slide/showSlide";
    }

    private void copySlide(Slide objSlide,Slide slide) {
        objSlide.setSlideShow(slide.getSlideShow());
        org.apache.poi.hslf.model.Shape[] shapes = slide.getShapes();
        for ( org.apache.poi.hslf.model.Shape shape : shapes) {
            objSlide.addShape(shape);

        }
    }
}
