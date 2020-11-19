package com.cyq.cyq.controller;

import com.cyq.cyq.model.User;
import com.cyq.cyq.service.SendEmailService;
import com.cyq.cyq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value ="/user" )
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private User user;
    @Autowired
    private SendEmailService sendEmailService;

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
        String email="";
        User newuser = new User();
        try{
            HttpSession session = request.getSession(false);
            username = (String)session.getAttribute("username");
            User userx= userService.getUserByName(username);
            oldpassword=userx.getPassword();
            email = userx.getEmail();
            password=user.getPassword();
            if(!oldpassword.equals(password)){
                map.put("msg","fail");
            }else {
                userid = userx.getUserid();
                newuser.setUserid(userid);
                newuser.setPassword(user.getNewpassword());
                userService.editUser(newuser);
                sendEmailService.sendEmaileditpassword(username,email,password);
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
    @ResponseBody
    public Map<String,Object> getAllUser (HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List <User> userlist = new ArrayList<User>();
        try {
            userlist = userService.getUser();
            resultmap.put("data",userlist);
            resultmap.put("code","1000");
            resultmap.put("msg","");
            resultmap.put("code","0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultmap;
    }
    @RequestMapping(value = "/deleteuser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteuser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userid =request.getParameter("id");
        try {
            userService.deleteUser(userid);
            map.put("msg","success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/editrole", method = RequestMethod.GET)
    public ModelAndView edituserrolename(HttpServletRequest request) {
        String userid=request.getParameter("userid");
        String username=request.getParameter("username");
        ModelAndView mav = new ModelAndView("user/editrole");
        mav.addObject("userid",userid);
        mav.addObject("username",username);
        return mav;
    }
    @RequestMapping(value = "/notadmin", method = RequestMethod.GET)
    public ModelAndView notadmin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/notadmin");
        return mav;
    }

    @RequestMapping(value = "/getnormaluser",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getnormaluser (HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List <User> userlist = new ArrayList<User>();
        try {
            userlist = userService.getNormalUser();
            resultmap.put("data",userlist);
            resultmap.put("code","1000");
            resultmap.put("msg","");
            resultmap.put("code","0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultmap;
    }

    @RequestMapping(value = "/deleteuseranyway",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteuseranyway(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userid =request.getParameter("userid");
        try {
            userService.deleteUser(userid);
            map.put("msg","success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value = "/userdaka", method = RequestMethod.GET)
    public ModelAndView userdaka(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/userdaka");
        return mav;
    }
    @RequestMapping(value = "/userup",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> userup(HttpServletRequest request, HttpServletResponse response,String flag) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            User user = new User();
            HttpSession session = request.getSession(false);
            String username = (String)session.getAttribute("username");
            user.setUsername(username);
            user.setDate(date);
            user.setFlag(flag);
            int count = userService.insertUserDaKa(user);
            if(count>0){
                map.put("msg","success");
            }else{
                map.put("msg","fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping(value = "/lookuserdaka", method = RequestMethod.GET)
    public ModelAndView lookuserdaka(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user/alluserdaka");
        return mav;
    }
    @RequestMapping(value = "/getalluserdaka",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getalluserdaka (HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List <User> userdakalist = new ArrayList<User>();
        try {
            userdakalist = userService.getUserDaKa();
            for(User user:userdakalist){
                if(user.getFlag().equals("up")){
                    user.setFlag("上班打卡");
                }else{
                    user.setFlag("下班打卡");
                }
            }
            resultmap.put("data",userdakalist);
            resultmap.put("code","1000");
            resultmap.put("msg","");
            resultmap.put("code","0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultmap;
    }

}
