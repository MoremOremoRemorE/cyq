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
@RequestMapping(value ="/pdf" )
public class PdfController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/pdflogin", method = RequestMethod.GET)
    public ModelAndView pdflogin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("custom/customlist");
        return mav;
    }

}
