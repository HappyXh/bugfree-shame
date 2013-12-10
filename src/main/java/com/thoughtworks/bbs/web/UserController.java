package com.thoughtworks.bbs.web;

import com.thoughtworks.bbs.model.Post;
import com.thoughtworks.bbs.model.User;
import com.thoughtworks.bbs.model.UserRole;
import com.thoughtworks.bbs.service.PostService;
import com.thoughtworks.bbs.service.UserService;
import com.thoughtworks.bbs.service.impl.PostServiceImpl;
import com.thoughtworks.bbs.service.impl.UserServiceImpl;
import com.thoughtworks.bbs.util.MyBatisUtil;
import com.thoughtworks.bbs.util.UserBuilder;
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
import java.io.Console;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private PostService postService;

    public UserController() {
        userService = new UserServiceImpl(MyBatisUtil.getSqlSessionFactory());
        postService = new PostServiceImpl(MyBatisUtil.getSqlSessionFactory());
    }

    public UserController setPostService(PostService postService){
        this.postService = postService;
        return this;
    }

    public UserController setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }


    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView registerUser(ModelMap model) {
        return new ModelAndView("user/register");
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public ModelAndView userProfile(ModelMap model, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Map<String, User> map = new HashMap<String, User>();
        map.put("user", user);

        model.addAttribute("myPosts", postService.findMainPostByAuthorNameSortedByCreateTime(principal.getName()));
        return new ModelAndView("user/profile", map);
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.POST)
    public ModelAndView processDeletePost(HttpServletRequest request, Principal principal, ModelMap model) {
        User user = userService.getByUsername(principal.getName());
        Map<String, User> map = new HashMap<String, User>();
        map.put("user", user);

        String deletePostId = request.getParameter("deletePost");

        postService.deleteAllPostsByMainPost(Long.parseLong(deletePostId));

        List<Post> myPosts = postService.findMainPostByAuthorNameSortedByCreateTime(principal.getName());
        model.addAttribute("myPosts", myPosts);
        return new ModelAndView("user/profile", map);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserBuilder builder = new UserBuilder();
        builder.userName(username).password(password).enable(true);

        userService.save(builder.build());
        User user = userService.getByUsername(username);

        Map<String, User> map = new HashMap<String, User>();
        map.put("user", user);

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

        List<Post> myPosts = postService.findMainPostByAuthorNameSortedByCreateTime(principal.getName());
        model.addAttribute("myPosts", myPosts);

        if (userService.passwordVerify(user, password) && userService.password(user, newPasswd)){
            model.addAttribute("success", "Password changed successfully.");
            return new ModelAndView("user/profile", map);
        }
        model.addAttribute("error", "Failed to change password.");
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
        model.put("usersWithRoles",usersWithRoles);
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
            List<Post> myPosts = postService.findMainPostByAuthorNameSortedByCreateTime(principal.getName());

            for (int k = 0;k < myPosts.size();k++){
                Post post = myPosts.get(k);
                post.setAuthorName(newUsername);
                postService.save(post);
            }

            userService.update(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(newUsername,user.getPasswordHash());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            map.clear();
            map.put("user",user);
            model.addAttribute("myPosts", myPosts);
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

        List<Post> posts = postService.findMainPostByAuthorNameSortedByCreateTime(showUser.getUserName());
        map.put("user",user);
        map.put("myPosts", posts);
        map.put("showUser", showUser);
        return new ModelAndView("user/profile", map);
    }

}
