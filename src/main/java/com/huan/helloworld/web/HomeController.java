package com.huan.helloworld.web;

import com.huan.helloworld.model.Story;
import com.huan.helloworld.model.StoryLine;
import com.huan.helloworld.service.StoryService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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

    @RequestMapping(value="/{id}/select",method = RequestMethod.POST)
    public String getStoryLine(@PathVariable("id") int id, ModelMap map) throws IOException {
        Story story=storyService.findById(id);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StoryLine storyLine = objectMapper.readValue(story.getStoryLine(), StoryLine.class);
            System.out.println(storyLine.getTitle());

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "slide/selectSlide";
    }


    private void displayHomePage(Principal principal, ModelMap map) {
        List<Story> storyList=storyService.findAll();
        map.addAttribute("storyList",storyList);
    }

}
