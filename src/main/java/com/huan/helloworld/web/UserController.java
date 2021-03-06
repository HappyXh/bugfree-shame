package com.huan.helloworld.web;

import com.huan.helloworld.model.User;
import com.huan.helloworld.service.ServiceResult;
import com.huan.helloworld.service.UserService;
import com.huan.helloworld.util.MyBatisUtil;
import com.huan.helloworld.util.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView registerUser(ModelMap model) {
        return new ModelAndView("user/register");
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public ModelAndView userProfile(ModelMap model, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Map<String, User> map = new HashMap<String, User>();
        map.put("user", user);

        return new ModelAndView("user/profile", map);
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, Object> map = new HashMap<String, Object>();
        boolean isPwdValid = isLegalPwd(password);
        if(!isPwdValid){
            map.put("hasError", true);
            Map<String,String> errors = new HashMap<String, String>();
            errors.put("password","Password should only contains numbers, chars and underscore with length between 6 to 12");
            map.put("errors",errors);
            return new ModelAndView("user/createSuccess", map);
        }

        UserBuilder builder = new UserBuilder();
        builder.userName(username).password(password).enable(true);
        ServiceResult<User> result = userService.save(builder.build());

        if(result!=null){
           map.put("user", result.getModel());
           if(result.getErrors().isEmpty()){
               map.put("hasError", false);
           }else{
               map.put("hasError", true);
               map.put("errors",result.getErrors());
           }
        }
        return new ModelAndView("user/createSuccess", map);
    }

    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.GET)
    public ModelAndView changePassword(ModelMap model, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Map<String, User> map = new HashMap<String, User>();
        map.put("user", user);
        return new ModelAndView("user/changePassword", map);
    }

    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.POST)
    public ModelAndView processPasswordChange(HttpServletRequest request, Principal principal, ModelMap model) throws IOException {
        User user = userService.getByUsername(principal.getName());
        String password = request.getParameter("password");
        String newPasswd = request.getParameter("confirmPassword");
        Map<String, User> map = new HashMap<String, User>();
        map.put("user", user);
        if (isLegalPwd(newPasswd)&&userService.passwordVerify(user, password) && userService.password(user, newPasswd)){
            model.addAttribute("success", "Password changed successfully.");
        }else{
            model.addAttribute("error", "Failed to change password.");
        }
        return new ModelAndView("user/profile", map);
    }

    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public ModelAndView showAllUsers(ModelMap map) {
        Map <User,String> usersWithRoles= userService.getAllUsersWithRole();
        map.put("usersWithRoles", usersWithRoles);
        return new ModelAndView("user/users", map);
    }

    @RequestMapping(value = {"/users"}, method = RequestMethod.POST)
    public ModelAndView authoriseUser(HttpServletRequest request,ModelMap model) {
        userService.authoriseUser(Long.parseLong(request.getParameter("authoriseUserId")));
        Map <User,String> usersWithRoles= userService.getAllUsersWithRole();
        model.put("usersWithRoles", usersWithRoles);
        return new ModelAndView("user/users", model);
    }

    @RequestMapping(value = {"/disableUser"}, method = RequestMethod.POST)
    public ModelAndView processUserDisable(HttpServletRequest request, ModelMap model) {
        String userName = request.getParameter("userName");
        User user = userService.getByUsername(userName);
        userService.disable(user);
        Map<String,User> map = new HashMap<String,User>();
        Map <User,String> usersWithRoles= userService.getAllUsersWithRole();
        model.put("usersWithRoles",usersWithRoles);
        return new ModelAndView("redirect:users",map);
    }

    @RequestMapping(value = {"/updateProfile"}, method = RequestMethod.GET)
    public ModelAndView changeUsername(ModelMap model,Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Map<String,User> map = new HashMap<String,User>();
        map.put("user",user);
        return new ModelAndView("user/updateProfile",map);
    }

    @RequestMapping(value = {"/updateProfile"}, method = RequestMethod.POST)
    public ModelAndView processUpdateUsername(HttpServletRequest request,ModelMap model,Principal principal) throws IOException {
        User user = userService.getByUsername(principal.getName());
        String newUsername = request.getParameter("newUsername");
        Map<String, User> map = new HashMap<String, User>();
        map.put("user", user);
        boolean isNewnameEmpty ="".equals(newUsername);
        User newuser= userService.getByUsername(newUsername);

        if (isNewnameEmpty == false && newuser == null){
            user.setUserName(newUsername);
            userService.update(user);
            Authentication oldAuthentication = SecurityContextHolder.getContext().getAuthentication();
            Authentication authentication = null;
            if(oldAuthentication == null){
                authentication = new UsernamePasswordAuthenticationToken(newUsername,user.getPasswordHash());
            }else{
                authentication = new UsernamePasswordAuthenticationToken(newUsername,user.getPasswordHash(),oldAuthentication.getAuthorities());
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            map.clear();
            map.put("user",user);
            model.addAttribute("namesuccess", "User Profile updated successfully");
            return new ModelAndView("user/profile", map);
        }
        model.addAttribute("error", "User Profile update failed");
        return new ModelAndView("user/updateProfile", map);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public ModelAndView showUserProfile(@PathVariable("id") Long id, Principal principal, ModelMap model) {
        Map<String, Object> map = new HashMap<String, Object>();
        User showUser = userService.get(id);
        User user = userService.getByUsername(principal.getName());

        map.put("user",user);
        map.put("showUser", showUser);
        return new ModelAndView("user/profile", map);
    }

    private boolean isLegalPwd(String pwd){
        if(pwd==null || pwd.trim().isEmpty()) return false;
        String regex = "^[a-zA-Z0-9]\\w{5,11}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

}
