package com.huan.helloworld.web;

import com.huan.helloworld.model.Slide;
import com.huan.helloworld.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by happy on 7/25/2015.
 */
@Controller
@RequestMapping("/slide")
public class SlideController {
    @Autowired
    SlideService slideService;

    @RequestMapping(value="/createPPT",method = RequestMethod.POST)
    public String createPPT( HttpServletRequest request,ModelMap map)  {
        String slidesStr = request.getParameter("slides");
        String[] slidesArr=slidesStr.split(",");
        try {
            for(int i=1;i<slidesArr.length;i++){
                Slide slide=slideService.findById(Integer.parseInt(slidesArr[i]));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "slide/success";

    }
}
