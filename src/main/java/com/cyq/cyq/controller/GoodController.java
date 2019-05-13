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
import java.text.SimpleDateFormat;
import java.util.*;

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

        for(GoodSort goodSort:goodsortlist) {
            if(goodSort.getGoodsortstatus().equals("0")){
                goodSort.setGoodsortstatus("启用");
            }else{
                goodSort.setGoodsortstatus("停用");
            }
        }
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

    @RequestMapping(value = "/addgoodsort",method = RequestMethod.GET)
    public ModelAndView addgoodsort(HttpServletRequest request) {
        String goodsortid=request.getParameter("goodsortid");
        String goodsortname=request.getParameter("goodsortname");
        ModelAndView mav = new ModelAndView("good/addgoodsort");
        mav.addObject("goodsortid",goodsortid);
        mav.addObject("goodsortname",goodsortname);
        return mav;
    }

    @RequestMapping(value = "/editgoodsort",method = RequestMethod.GET)
    public ModelAndView editgoodsort(HttpServletRequest request) {
        String goodsortid=request.getParameter("goodsortid");
        String goodsortname=request.getParameter("goodsortname");
        String goodsortstatus=request.getParameter("goodsortstatus");

        ModelAndView mav = new ModelAndView("good/editgoodsort");
        mav.addObject("goodsortid",goodsortid);
        mav.addObject("goodsortname",goodsortname);
        mav.addObject("goodsortstatus",goodsortstatus);
        return mav;
    }

    @RequestMapping(value = "/editgoodsortinfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editgoodsortinfo(HttpServletRequest request, HttpServletResponse response, GoodSort goodSort){
        Map<String,Object> map = new HashMap<String,Object>();
        GoodSort nwegoodSort = new GoodSort();
        try{
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String updatetime = sdf.format(date);
            nwegoodSort.setUpdatetime(updatetime);
            nwegoodSort.setGoodsortid(goodSort.getGoodsortid());
            nwegoodSort.setGoodsortstatus(goodSort.getGoodsortstatus());
            nwegoodSort.setGoodsortname(goodSort.getGoodsortname());
            goodService.editGoodSort(nwegoodSort);
            map.put("data","success");
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/addgoodsortinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addgoodsort(GoodSort goodSort) {
        Map<String,Object> map = new HashMap<String,Object>();
        String goodsortname =goodSort.getGoodsortname();
        GoodSort newgoodSort = new GoodSort();
        try {
            int count = goodService.checkName(goodsortname);
            if(count > 0){
                map.put("msg","fail");
            }else{
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                newgoodSort.setCreattime(time);
                newgoodSort.setGoodsortname(goodsortname);
                newgoodSort.setGoodsortstatus(goodSort.getGoodsortstatus());
                newgoodSort.setGoodsortpid(goodSort.getGoodsortid());
                goodService.addGoodSort(newgoodSort);
                map.put("msg","success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
