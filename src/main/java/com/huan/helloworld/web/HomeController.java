package com.huan.helloworld.web;

import com.huan.helloworld.model.Story;
import com.huan.helloworld.service.StoryService;
import com.huan.helloworld.service.UserService;
import com.huan.helloworld.util.MyBatisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    StoryService storyService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(ModelMap map, Principal principal) {
        if (null == principal) {
            return new ModelAndView("login");
        }
        displayHomePage(principal, map);
        return new ModelAndView("home",map);
    }

    @RequestMapping(value="select",method = RequestMethod.GET)
    public String getStoryLine(Model model, Principal principal) {

        return "slide/selectSlide";
    }


    private void displayHomePage(Principal principal, ModelMap map) {
        List<Story> storyList=storyService.getAll();
        map.addAttribute("storyList",storyList);
    }

}
