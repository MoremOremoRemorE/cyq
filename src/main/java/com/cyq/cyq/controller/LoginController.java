package com.cyq.cyq.controller;

import com.alibaba.fastjson.JSON;
import com.cyq.cyq.model.User;
import com.cyq.cyq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/front" )
public class LoginController {
    @Autowired
    private UserService userService;
    /*@Autowired
    private MenuService menuService;*/
    @Autowired
    private User user;
    /*@Autowired
    private Menu menu;*/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("login/login");
        return mav;
    }

    @RequestMapping(value = "/logincheck",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checklogin1(HttpServletRequest req, HttpServletResponse resp,User user) {

        Map<String,Object> map = new HashMap<String,Object>();
        String username =user.getUsername();
        String password =user.getPassword();
        try {
            User userinfo= new User();
            userinfo = userService.getUserByName(username);
            String roleid = userinfo.getRoleid();
            if(userinfo == null ){
                map.put("result","false");
            }else if(!userinfo.getPassword().equals( password)){
                map.put("result","pserr");
            }else{
                map.put("result","success");
            }
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            session.setAttribute("roleid",roleid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //      System.out.println(map);
        return map;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        List<User> userinfolist = null;
   //     List<Menu> menuData = null;
        String username ="";
        String msg="";
        String roleid="";
        try {
            HttpSession session = request.getSession(false);
            username = (String)session.getAttribute("username");
            //得到roleid
            roleid = (String)session.getAttribute("roleid");
            //根据roleid得到不同的菜单
     //       menuData = menuService.getMenu(roleid);
            userinfolist = userService.getUser();


        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("login/index");
        mav.addObject("msg",msg);
        mav.addObject("userinfolist", userinfolist);
        mav.addObject("username",username);
  //      mav.addObject("menuData",JSON.toJSONString(menuData));
        return mav;
    }

    @RequestMapping(value = "/console", method = RequestMethod.GET)
    public ModelAndView console(){
        ModelAndView mav= new ModelAndView("main/console");
        return mav;
    }

}
