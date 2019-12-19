package com.cyq.cyq.controller;

import com.cyq.cyq.model.CustomerInfo;
import com.cyq.cyq.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value ="/custom" )
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customlist", method = RequestMethod.GET)
    public ModelAndView goodlist(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("custom/customlist");
        return mav;
    }

    @RequestMapping(value = "/getallcustom",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getallcustom (HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        List<CustomerInfo> customerinfolist = new ArrayList<CustomerInfo>();
        try {
            customerinfolist = customerService.getCustomer();
            resultmap.put("data",customerinfolist);
            resultmap.put("code","1000");
            resultmap.put("msg","");
            resultmap.put("code","0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultmap;
    }

    @RequestMapping(value = "/deletecustom",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deletecustom(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String userid =request.getParameter("id");
        try {
            customerService.deleteCustomer(userid);
            map.put("msg","success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/editcustominfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editcustom(HttpServletRequest request, HttpServletResponse response,CustomerInfo customerInfo){
        Map<String,Object> map = new HashMap<String,Object>();
        CustomerInfo customerInfo1 = new CustomerInfo();
        try{
       //     String  userid = customerInfo.getUserid();
       //     String phone = customerInfo.getPhone();
        //    customerInfo1.setPhone(phone);
       //     customerInfo1.setUserid(userid);
            int count = customerService.updateCustomerById(customerInfo1);

            map.put("data","success");
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/editcustom", method = RequestMethod.GET)
    public ModelAndView editcustom(HttpServletRequest request) {
        String userid=request.getParameter("userid");
        String phone=request.getParameter("phone");
        String username=request.getParameter("username");
        ModelAndView mav = new ModelAndView("custom/editcustom");
        mav.addObject("userid",userid);
        mav.addObject("phone",phone);
        mav.addObject("username",username);
        return mav;
    }

    //对接小程序
    @RequestMapping(value = "/insertcustomer", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertcustomer(@RequestBody CustomerInfo customerInfo, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        String customerid= UUID.randomUUID().toString().replace("-","");
        customerInfo.setCustomerid(customerid);
        int count = customerService.insertCustomerInfo(customerInfo);
        if(count>0){
            map.put("msg","success");
        }else{
            map.put("msg","fail");
        }
        return map;

    }
}
