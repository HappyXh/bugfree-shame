package com.huan.helloworld.web;

import com.huan.helloworld.model.SlidePage;
import com.huan.helloworld.model.Story;
import com.huan.helloworld.model.StoryLine;
import com.huan.helloworld.model.SubPart;
import com.huan.helloworld.service.SlidePageService;
import com.huan.helloworld.service.StoryService;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
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

        String slideIdStr = request.getParameter("slideIdStr");
        String[] slidesArr=slideIdStr.split(",");

        ComThread.InitSTA();
        ActiveXComponent pptApp = new ActiveXComponent("PowerPoint.Application");
        Dispatch.put(pptApp, "Visible", new Variant(true));

        Dispatch presentations = pptApp.getProperty("Presentations").toDispatch();
        // 打开输出文件
        Dispatch outputPresentation = Dispatch.call(presentations, "add").toDispatch();
        Dispatch outputSlides = Dispatch.call(outputPresentation, "Slides").toDispatch();
        int outputPageNum ;
        for (int i=0;i<slidesArr.length;i++){
            SlidePage sp=slidePageService.findById(Integer.parseInt(slidesArr[i]));
            outputPageNum = Dispatch.get(outputSlides, "Count").getInt();
            Dispatch.call(outputSlides, "InsertFromFile","D:/Project/Pointhinker/resource/"+sp.getFilePath().substring(38), outputPageNum, sp.getPage(), sp.getPage());
        }
        Dispatch.call(outputPresentation, "SaveAs","D:/test.ppt");
        Dispatch.call(outputPresentation, "Close");
        pptApp.invoke("Quit");
        ComThread.Release();
        return "slide/success";

    }

    @RequestMapping(value="{id}/{index}/select",method = RequestMethod.POST)
    public @ResponseBody String[] chooseAjax( @PathVariable("id") int id,@PathVariable("index") int index, HttpServletRequest request,ModelMap map)  {
        List<SlidePage> slidePages=slidePageService.fiinAll();
        ArrayList<Integer> list = new ArrayList<Integer>();
        switch(index){
            case 0:
                list.add(2);
                list.add(41);
                list.add(53);
                list.add(62);
                list.add(71);
                break;
            case 1:
                list.add(42);
                break;
            case 2:
                list.add(43);
                list.add(44);
                break;
            case 3:
                list.add(45);
                list.add(46);
                list.add(47);
                break;
            case 4:
                list.add(48);
                list.add(49);
                break;
            case 5:
                list.add(50);
                break;
            case 6:
                list.add(51);
                list.add(52);
                break;
            case 7:
                list.add(76);
                list.add(77);
                break;
            case 8:
                list.add(78);
                break;
            case 9:
                list.add(79);
                break;
            case 10:
                list.add(13);
                list.add(80);
                break;
            default:
                //        int MAX_NUM=12;
                slidePages.remove(0);
                int MAX_NUM=slidePages.size();
                Random r = new Random();
                int i;
                while(list.size() < 10){
                    i = r.nextInt(MAX_NUM-1)+ 1;
                    if(!list.contains(i)){
                        list.add(slidePages.get(i).getId());
                    }
                }
        }

        String[] str=new String[2];
        str[1]="[";
        for(int i=0;i<list.size();i++){
            str[1]+=slidePageService.findById(list.get(i)).toString()+",";
        }
        str[1]=str[1].substring(0,str[1].length()-1)+"]";
        str[0]="1";

        return str;
    }

    @RequestMapping(value="{id}/test",method = RequestMethod.POST)
    public @ResponseBody String[] test( @PathVariable("id") int id, HttpServletRequest request){
        String slideIdStr = request.getParameter("slideIdStr");
        String[] slidesArr=slideIdStr.split(",");
        String[] str=new String[1];

        str[0]="[";
        for(int i=0;i<slidesArr.length;i++){
            str[0]+=slidePageService.findById(Integer.parseInt(slidesArr[i])).toString()+",";
        }
        str[0]=str[0].substring(0,str[0].length()-1)+"]";

        return str;
    }

    @RequestMapping(value="/{id}/reSelect",method = RequestMethod.POST)
    public String getStoryLine(@PathVariable("id") int id, ModelMap map,HttpServletRequest request) throws IOException {
        String slideIdStr = request.getParameter("slideIdStr");
        String selectedSlides = request.getParameter("selectedSlides");
        String storyLineStr=request.getParameter("storyLineStr");

        map.addAttribute("selectedSlides",selectedSlides);

        String[] slideArr=slideIdStr.split(",");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StoryLine storyLine = objectMapper.readValue(storyLineStr, StoryLine.class);
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

}
