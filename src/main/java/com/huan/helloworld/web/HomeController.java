package com.huan.helloworld.web;

import com.huan.helloworld.model.Slides;
import com.huan.helloworld.model.Story;
import com.huan.helloworld.service.SlideService;
import com.huan.helloworld.service.StoryService;
import com.huan.helloworld.util.ElasticSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    StoryService storyService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(ModelMap map) {

        return new ModelAndView("index",map);
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView getHome(ModelMap map) {
        List<Story> storyList = storyService.findAll();
        Random r = new Random();
        int i = r.nextInt(storyList.size()-4);
        map.addAttribute("storyList", storyList.subList(i,i+3));
        return new ModelAndView("home",map);
    }

    @RequestMapping(value="/about", method = RequestMethod.GET)
    public ModelAndView getAbout(ModelMap map) {

        return new ModelAndView("about",map);
    }
    @RequestMapping(value="/help", method = RequestMethod.GET)
    public ModelAndView getHelp(ModelMap map) {

        return new ModelAndView("help",map);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody String[] getSotry( HttpServletRequest request) {
        String features = request.getParameter("features");
        return search(features);
    }

    //search on elasticSearch
    public String[] search(String features) {
        String  param = "q=features:"+
                features.replaceAll("\\s+", ",") +
//                features.replaceAll("[^a-zA-Z\\s]"," ").replaceAll("\\s+", ",") +
                "&_source=false";

        String[] slides_str = new String[1];
        String result = ElasticSearch.sendGet(ElasticSearch.GET_STORY_URL, param);
        result = result.substring(result.indexOf("hits")+6,result.length()-3);
        slides_str[0] = result.substring(result.indexOf("hits")+6);
        return slides_str;
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
