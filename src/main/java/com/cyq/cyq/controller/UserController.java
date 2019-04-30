package com.cyq.cyq.controller;

import com.cyq.cyq.model.User;
import com.cyq.cyq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        try {
            HttpSession session = request.getSession(false);
            username = (String)session.getAttribute("username");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("user/userinfo");
        mav.addObject("username",username);
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

}
