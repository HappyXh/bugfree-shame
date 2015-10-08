package com.huan.helloworld.web;

import com.huan.helloworld.model.Dictionary;
import com.huan.helloworld.model.Slides;
import com.huan.helloworld.service.DictionaryService;
import com.huan.helloworld.service.SlideService;

import com.huan.helloworld.util.LocalMysql;
import com.huan.helloworld.util.ReadPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by happy on 7/25/2015.
 */
@Controller
@RequestMapping("/slide")
public class SlideController {
    @Autowired
    SlideService slideService;
    @Autowired
    DictionaryService dictionaryService;


    private static final String OUTPUT_PATH = "resource/output";

    @RequestMapping(method = RequestMethod.GET)
    public String showSlides(ModelMap map) {
        int[] slide_arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        List<Slides> slidesList = new ArrayList<>();
        for (int i : slide_arr) {
            slidesList.add(slideService.findById(i));
        }
        map.addAttribute("slidesList", slidesList);
        return "slide/slide";
    }


    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody String[] searchSlides( HttpServletRequest request) {
        String features = request.getParameter("features");
        return search(features);
    }

        @RequestMapping(value = "getSlide/{id}", method = RequestMethod.POST)
    public @ResponseBody String[] getSlide(@PathVariable("id") int id, ModelMap map, HttpServletRequest request) {
        String[] slides_str = new String[1];
        slides_str[0] = "[";
        slides_str[0] += slideService.findById(id).toString() + "]";
        return slides_str;
    }

    @RequestMapping(value="/createPPT",method = RequestMethod.POST)
    public String createPPT( HttpServletRequest request,ModelMap map)  {
        String slideIdStr = request.getParameter("slideIdArr");
        String[] slideIdArr = slideIdStr.split(",");
        String fileName = UUID.randomUUID() + ".pdf";
        ReadPDF myReadPDF = new ReadPDF(request.getSession().getServletContext().getRealPath("/")+
                "/attachment/"+fileName);
        LocalMysql myConn=new LocalMysql();
        List<Integer> slides = new ArrayList<>();
        for (String str : slideIdArr) {
            slides.add(Integer.parseInt(str));
        }
        try {
            myReadPDF.createPDF(slides, myConn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        myConn.close();
        return "redirect:http://www.poinThinker.com/attachment/"+fileName;
    }

    public String[] search(String features){
        String[] features_arr = features.replaceAll("\\s+", "").split(",");
        String ids = "";
        for (String str : features_arr) {
            ids += dictionaryService.getIdsByWord(str) + ",";
        }
        String[] ids_arr = ids.split(",");
        List<String> str_list = new ArrayList<String>();
        for (int i=0; i<ids_arr.length; i++) {
            if(!str_list.contains(ids_arr[i])) {
                str_list.add(ids_arr[i]);
            }
        }

        int count = 0;
        String[] slides_str = new String[1];
        slides_str[0] = "[";
        for (String str : str_list) {
            try {
                count += 1;
                if (count > 20) break;
                slides_str[0] += slideService.findById(Integer.parseInt(str)).toString() + ",";
            } catch (Exception e) {

            }
        }
        slides_str[0] = slides_str[0].substring(0, slides_str[0].length() - 1) + "]";

        return slides_str;
    }

}
