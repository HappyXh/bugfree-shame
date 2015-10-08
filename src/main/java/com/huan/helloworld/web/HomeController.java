package com.huan.helloworld.web;

import com.huan.helloworld.model.Slides;
import com.huan.helloworld.service.SlideService;
import com.huan.helloworld.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    SlideService slideService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(ModelMap map) {
        List<Slides> slideList = slideService.findAll();
        map.addAttribute("slideList", slideList.subList(0,3));
        return new ModelAndView("home",map);
    }

    @RequestMapping(value="/about", method = RequestMethod.GET)
    public ModelAndView getAbout(ModelMap map) {

        return new ModelAndView("about",map);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView get(ModelMap map, Principal principal) {
//        if (null == principal) {
//            return new ModelAndView("login");
//        }
//        List<Slides> slideList = slideService.findAll();
//        map.addAttribute("slideList", slideList.subList(0,3));
//        return new ModelAndView("home",map);
//    }


//    private void displayHomePage(Principal principal, ModelMap map) {
//        List<Story> storyList=storyService.findAll();
//        map.addAttribute("storyList",storyList);
//    }

}
