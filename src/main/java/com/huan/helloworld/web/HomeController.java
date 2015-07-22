package com.huan.helloworld.web;

import com.huan.helloworld.service.UserService;
import com.huan.helloworld.util.MyBatisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, Principal principal) {
        if (null == principal) {
            return "login";
        }
        displayHomePage(principal, model);
        return "home";
    }

    @RequestMapping(value="select",method = RequestMethod.GET)
    public String getStoryLine(Model model, Principal principal) {

        return "slide/selectSlide";
    }


    private void displayHomePage(Principal principal, Model model) {

    }

}
