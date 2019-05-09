package com.cyq.cyq.controller;

import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.model.User;
import com.cyq.cyq.service.GoodService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/good" )
public class GoodController {
    @Autowired
    private GoodService goodService ;
    @Autowired
    private GoodSort goodSort ;

    @RequestMapping(value = "/goodlist", method = RequestMethod.GET)
    public ModelAndView goodlist(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("good/allgood");
        return mav;
    }

    @RequestMapping(value = "/goodsort", method = RequestMethod.GET)
    public ModelAndView goodsort(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("good/goodsort");
        return mav;
    }

    @RequestMapping(value = "/getallgoodsort",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllUser (HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String,Object> resultmap = new HashMap<String,Object>();

        List<GoodSort> goodsortlist = goodService.getGoodSortList();
        resultmap.put("data",goodsortlist);
        resultmap.put("code","1000");
        resultmap.put("msg","ok");
        resultmap.put("code","0");

        return resultmap;
    }

    @RequestMapping(value = "/deletgoodsort",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deletgood(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String goodsortid =request.getParameter("id");
        try {
            goodService.deleteGoodSort(goodsortid);
            map.put("msg","success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
