package com.cyq.cyq.controller;

import com.cyq.cyq.model.User;
import com.cyq.cyq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/user" )
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private User user;

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public ModelAndView userinfo(HttpServletRequest request) {
        String username ="";
        String roleid="";
        String phone="";
        String note="";
        String sex="";
        try {
            HttpSession session = request.getSession(false);
            username = (String)session.getAttribute("username");
            User user= userService.getUserByName(username);
            roleid=user.getRoleid();
            phone=user.getPhone();
            note=user.getNote();
            sex=user.getSex();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("user/userinfo");
        mav.addObject("username",username);
        mav.addObject("roleid",roleid);
        mav.addObject("phone",phone);
        mav.addObject("note",note);
        mav.addObject("sex",sex);
        return mav;
    }
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView password(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/password");
        return mav;
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("login/login");
        return mav;
    }
    @RequestMapping(value = "/edituserinfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> edituserinfo(HttpServletRequest request, HttpServletResponse response, User user){
        Map<String,Object> map = new HashMap<String,Object>();
       User newuser = new User();
       try{
           String  username = user.getUsername();
           User userx= userService.getUserByName(username);
           String userid=userx.getUserid();

           newuser.setUserid(userid);
           newuser.setRoleid(user.getRoleid());
           newuser.setPhone(user.getPhone());
           newuser.setNote(user.getNote());
           newuser.setSex(user.getSex());
           userService.editUser(newuser);
            map.put("data","success");
       }catch(Exception e){
           e.printStackTrace();
       }
       return map;
    }

    @RequestMapping(value = "/editpassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editpassword(HttpServletRequest request, HttpServletResponse response, User user){
        Map<String,Object> map = new HashMap<String,Object>();
        String username="";
        String userid="";
        String oldpassword="";
        String password="";
        User newuser = new User();
        try{
            HttpSession session = request.getSession(false);
            username = (String)session.getAttribute("username");
            User userx= userService.getUserByName(username);
            oldpassword=userx.getPassword();
            password=user.getPassword();
            if(!oldpassword.equals(password)){
                map.put("msg","fail");
            }else {
                userid = userx.getUserid();
                newuser.setUserid(userid);
                newuser.setPassword(user.getNewpassword());
                userService.editUser(newuser);
                map.put("msg", "successs");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value = "/alluser", method = RequestMethod.GET)
    public ModelAndView alluser(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/alluser");
        return mav;
    }

    @RequestMapping(value = "/getalluser",method =RequestMethod.GET)
    public List<User> getAllUser (HttpServletRequest request,HttpServletResponse response){
        List <User> userlist = new ArrayList<User>();
        try {
            userlist = userService.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }
}
