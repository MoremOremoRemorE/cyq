package com.cyq.cyq.controller;

import com.cyq.cyq.model.User;
import com.cyq.cyq.service.GoodService;
import com.cyq.cyq.service.UserService;
import com.cyq.cyq.service.SendEmailService;
import com.cyq.cyq.system.dto.AskResult;
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
@RequestMapping(value ="/front" )
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private User user;
    @Autowired
    private SendEmailService sendEmailService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("login/login");
        return mav;
    }

    @RequestMapping(value = "/logincheck",method = RequestMethod.POST)
    @ResponseBody
    public AskResult checklogin1(HttpServletRequest req, HttpServletResponse resp, User user) {
        //清除上一个用户的遗留session
        HttpSession fsession = req.getSession(false);
        if(null !=fsession&&!fsession.isNew()){
            fsession.invalidate();
        }
        Map<String,Object> map = new HashMap<String,Object>();
        String username =user.getUsername();
        String password =user.getPassword();
        try {
            HttpSession session = req.getSession(true);
            User userinfo = userService.getUserByName(username);
            if(userinfo == null ){
                return AskResult.success("false");
            }else if(!userinfo.getPassword().equals( password)){
                return AskResult.success("pserr");
            }else{
                String roleid = userinfo.getRoleid();
                String userid = userinfo.getUserid();
                session.setMaxInactiveInterval(60*60*5);
                session.setAttribute("username",username);
                session.setAttribute("roleid",roleid);
                session.setAttribute("userid",userid);
                return AskResult.success("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AskResult.failed("用户验证异常,请联系维护人员!");
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        List<User> userinfolist = null;
   //     List<Menu> menuData = null;
        String username ="";
        String msg="";
        String userid="";
        String roleid="";
        try {
            HttpSession session = request.getSession(false);
            username = (String)session.getAttribute("username");
            userid = (String)session.getAttribute("userid");
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
        mav.addObject("userid",userid);
  //      mav.addObject("menuData",JSON.toJSONString(menuData));
        return mav;
    }

    @RequestMapping(value = "/console", method = RequestMethod.GET)
    public ModelAndView console(){
        ModelAndView mav= new ModelAndView("main/console");
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView mav= new ModelAndView("login/register");
        return mav;
    }
    @RequestMapping(value = "/addregister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addregister(User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        String username =user.getUsername();
        String email = user.getEmail();
        String keywoed="register";
        //    String id= UUID.randomUUID().toString().replace("-", "").toLowerCase();
        try {
            int count = userService.regiterByName(username);
            if(count > 0){
                map.put("msg","fail");
            }else{
                //           user.setId(id);
                //我要获取当前的日期
                Date date = new Date();
                //设置要获取到什么样的时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //获取String类型的时间
                String time = sdf.format(date);
                user.setTime(time);
                user.setRoleid("2");//默认为普通员工
                String userid=UUID.randomUUID().toString().replace("-","");
                user.setUserid(userid);
                user.setSex("男");
                userService.addUser(user);
               /* String userid = userService.selectUserId();
                UserRole userrole = new UserRole();
                userrole.setRoleid(user.getRoleid());
                userrole.setUserid(userid);

                userService.addUserRole(userrole);*/
                sendEmailService.sendEmail(username,email,keywoed);
                map.put("msg","success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //    System.out.println(username);
        //   System.out.println(password);
        //   System.out.println(map);
        return map;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(){
        ModelAndView mav= new ModelAndView("main/main");
        return mav;
    }
}
