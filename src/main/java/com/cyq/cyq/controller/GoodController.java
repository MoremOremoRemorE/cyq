package com.cyq.cyq.controller;

import com.cyq.cyq.model.*;
import com.cyq.cyq.service.CustomerService;
import com.cyq.cyq.service.GoodDealService;
import com.cyq.cyq.service.GoodService;
import com.cyq.cyq.service.GoodSortService;
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

/**
 * @author chaoqun
 */
@Controller
@RequestMapping(value ="/good" )
public class GoodController {
    @Autowired
    private GoodSortService goodSortService;
    @Autowired
    private GoodSort goodSort;
    @Autowired
    private GoodService goodService;
    @Autowired
    private Good good;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GoodDealService goodDealService;

    @RequestMapping(value = "/getLayUITree", method = RequestMethod.POST)
    @ResponseBody
    public AskResult getLayUiTree() {
        LayUITree layUITree;
        try {
            layUITree = goodService.getLayUITree();
        } catch (Exception e) {
            e.printStackTrace();
            return AskResult.failed("加载商品分类失败!");
        }
        return AskResult.success(layUITree);
    }

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

    @RequestMapping(value = "/getallgoodsort", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resultmap = new HashMap<String, Object>();

        List<GoodSort> goodsortlist = goodSortService.getGoodSortList();

        for (GoodSort goodSort : goodsortlist) {
            if ("0".equals(goodSort.getGoodsortstatus())) {
                goodSort.setGoodsortstatus("启用");
            } else {
                goodSort.setGoodsortstatus("停用");
            }
        }
        resultmap.put("data", goodsortlist);
        resultmap.put("code", "1000");
        resultmap.put("msg", "ok");
        resultmap.put("code", "0");

        return resultmap;
    }

    @RequestMapping(value = "/deletgoodsort", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deletgood(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String goodsortid = request.getParameter("id");
        try {
            goodSortService.deleteGoodSort(goodsortid);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/addgoodsort", method = RequestMethod.GET)
    public ModelAndView addgoodsort(HttpServletRequest request) {
        String goodsortid = request.getParameter("goodsortid");
        String goodsortname = request.getParameter("goodsortname");
        ModelAndView mav = new ModelAndView("good/addgoodsort");
        mav.addObject("goodsortid", goodsortid);
        mav.addObject("goodsortname", goodsortname);
        return mav;
    }

    @RequestMapping(value = "/editgoodsort", method = RequestMethod.GET)
    public ModelAndView editgoodsort(HttpServletRequest request) {
        String goodsortid = request.getParameter("goodsortid");
        String goodsortname = request.getParameter("goodsortname");
        String goodsortstatus = request.getParameter("goodsortstatus");

        ModelAndView mav = new ModelAndView("good/editgoodsort");
        mav.addObject("goodsortid", goodsortid);
        mav.addObject("goodsortname", goodsortname);
        mav.addObject("goodsortstatus", goodsortstatus);
        return mav;
    }

    @RequestMapping(value = "/editgoodsortinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editgoodsortinfo(HttpServletRequest request, HttpServletResponse response, GoodSort goodSort) {
        Map<String, Object> map = new HashMap<String, Object>();
        GoodSort nwegoodSort = new GoodSort();
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String updatetime = sdf.format(date);
            nwegoodSort.setUpdatetime(updatetime);
            nwegoodSort.setGoodsortid(goodSort.getGoodsortid());
            nwegoodSort.setGoodsortstatus(goodSort.getGoodsortstatus());
            nwegoodSort.setGoodsortname(goodSort.getGoodsortname());
            goodSortService.editGoodSort(nwegoodSort);
            map.put("data", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/addgoodsortinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addgoodsort(GoodSort goodSort) {
        Map<String, Object> map = new HashMap<String, Object>();
        String goodsortname = goodSort.getGoodsortname();
        GoodSort newgoodSort = new GoodSort();
        try {
            int count = goodSortService.checkName(goodsortname);
            if (count > 0) {
                map.put("msg", "fail");
            } else {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                newgoodSort.setCreattime(time);
                newgoodSort.setGoodsortname(goodsortname);
                newgoodSort.setGoodsortstatus(goodSort.getGoodsortstatus());
                newgoodSort.setGoodsortpid(goodSort.getGoodsortid());
                goodSortService.addGoodSort(newgoodSort);
                map.put("msg", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/getallgood", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllgood(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        List<Good> goodlist = new ArrayList<Good>();
        try {
            goodlist = goodSortService.getGood();
            for (Good good : goodlist) {
                if ("0".equals(good.getStatus())) {
                    good.setStatus("启用");
                } else {
                    good.setStatus("停用");
                }
            }
            resultmap.put("data", goodlist);
            resultmap.put("code", "1000");
            resultmap.put("msg", "");
            resultmap.put("code", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultmap;
    }

    @RequestMapping(value = "/addgood", method = RequestMethod.GET)
    public ModelAndView addgood(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("good/addgood");
        return mav;
    }

    @RequestMapping(value = "/addgoodinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addgoodinfo(Good good) {
        Map<String, Object> map = new HashMap<String, Object>();
        String goodname = good.getGoodname();
        try {
            int count = goodService.checkName(goodname);
            if (count > 0) {
                map.put("msg", "fail");
            } else {
                goodService.addGood(good);
                map.put("msg", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/deletegood", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deletegood(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String goodid = request.getParameter("id");
        try {
            goodService.deleteGood(goodid);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/editgood", method = RequestMethod.GET)
    public ModelAndView editgood(HttpServletRequest request) {
        String goodid = request.getParameter("goodid");
        String goodname = request.getParameter("goodname");
        String address = request.getParameter("address");
        String outtime = request.getParameter("outtime");
        String price = request.getParameter("price");
        String memprice = request.getParameter("memprice");
        ModelAndView mav = new ModelAndView("good/editgood");
        mav.addObject("goodid", goodid);
        mav.addObject("goodname", goodname);
        mav.addObject("address", address);
        mav.addObject("price", price);
        mav.addObject("outtime", outtime);
        mav.addObject("memprice", memprice);


        return mav;
    }

    @RequestMapping(value = "/editgoodinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editgoodinfo(HttpServletRequest request, HttpServletResponse response, Good good) {
        Map<String, Object> map = new HashMap<String, Object>();
        String goodname = good.getGoodname();
        String goodid = good.getGoodid();
        Good newgood = new Good();
        try {
            newgood = goodService.selectGoodByName(goodname);
            String newgoodid = newgood.getGoodid();
            if (!goodid.equals(newgoodid)) {
                map.put("msg", "fail");
            }
           /* int count = goodService.checkName(goodname);
            if(count > 0){
                map.put("msg","fail");
            }*/
            else {
                goodService.editGood(good);
                map.put("data", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/coustomComplaint", method = RequestMethod.GET)
    public ModelAndView coustomComplaint(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("good/coustomComplaint");
        return mav;
    }

    @RequestMapping(value = "/goodsell", method = RequestMethod.GET)
    public ModelAndView goodsell(HttpServletRequest request) {
        //选择所有的商品信息
        List<Good> goodlist = goodService.selectAllGood();
        ModelAndView mav = new ModelAndView("good/goodsell");
        mav.addObject("goodlist", goodlist);
        return mav;
    }

    @RequestMapping(value = "/addgoodsell", method = RequestMethod.GET)
    public ModelAndView addgoodsell(HttpServletRequest request) {
        List<Good> goodlist = goodService.selectAllGood();
        HttpSession session = request.getSession(true);
        String operatorname = (String) session.getAttribute("username");
        String operatorid = (String) session.getAttribute("userid");
        ModelAndView mav = new ModelAndView("good/goodsell");
        //判断是否是以前的客户
        CustomerInfo customerInfo = customerService.selectCustomerByName(request.getParameter("username"));
        if (customerInfo != null) {
            GoodDeal goodDeal = new GoodDeal();
            String gohome = request.getParameter("gohome");
            if (gohome == null) {
                goodDeal.setGohomeflag(0);
            } else {
                goodDeal.setGohomeflag(1);
            }
            if (request.getParameter("fenqi") == null) {
                goodDeal.setFenqiflag(0);
            } else {
                goodDeal.setFenqiflag(1);
            }
            if (request.getParameter("fapiao") == null) {
                goodDeal.setFapiaoflag(0);
            } else {
                goodDeal.setFapiaoflag(1);
            }
            goodDeal.setGoodid(request.getParameter("goodmsg"));
            goodDeal.setAddress(request.getParameter("address"));
            goodDeal.setUsername(request.getParameter("username"));
            //      goodDeal.setUserid(customerInfo.getUserid());
            goodDeal.setPhone(request.getParameter("phone"));
            goodDeal.setNote(request.getParameter("note"));
            goodDeal.setDate(request.getParameter("date"));
            goodDeal.setContent(request.getParameter("content"));
            goodDeal.setFilepath(request.getParameter("file"));
            goodDeal.setOperatorid(operatorid);
            goodDeal.setOperatorname(operatorname);
            //更新商品交易表
            int count = goodDealService.insertGoodDealInfo(goodDeal);
            //更新商品数量
            int count1 = goodService.updateGoodNumberByGoodId(request.getParameter("goodmsg"));
            if (count > 0) {
                mav.addObject("status", 1000);
            } else {
                mav.addObject("status", 1001);
            }
        } else {
            CustomerInfo customerInfox = new CustomerInfo();
            //     customerInfox.setUsername(request.getParameter("username"));
            //    customerInfox.setPhone(request.getParameter("phone"));
            int countx = customerService.insertCustomerInfo(customerInfox);
            String userid = "";
            if (countx > 0) {
                mav.addObject("status", 999);
                userid = String.valueOf(customerService.selectMaxUserId());
            } else {
                mav.addObject("status", 998);
            }
            GoodDeal goodDealx = new GoodDeal();
            if (request.getParameter("gohome") == null) {
                goodDealx.setGohomeflag(0);
            } else {
                goodDealx.setGohomeflag(1);
            }
            if (request.getParameter("fenqi") == null) {
                goodDealx.setFenqiflag(0);
            } else {
                goodDealx.setFenqiflag(1);
            }
            if (request.getParameter("fapiao") == null) {
                goodDealx.setFapiaoflag(0);
            } else {
                goodDealx.setFapiaoflag(1);
            }
            goodDealx.setGoodid(request.getParameter("goodmsg"));
            goodDealx.setAddress(request.getParameter("address"));
            goodDealx.setUsername(request.getParameter("username"));
            goodDealx.setPhone(request.getParameter("phone"));
            goodDealx.setNote(request.getParameter("note"));
            goodDealx.setDate(request.getParameter("date"));
            goodDealx.setContent(request.getParameter("content"));
            goodDealx.setFilepath(request.getParameter("file"));
            goodDealx.setUserid(userid);
            goodDealx.setOperatorid(operatorid);
            goodDealx.setOperatorname(operatorname);
            //更新商品交易表
            int count = goodDealService.insertGoodDealInfo(goodDealx);
            int count1 = goodService.updateGoodNumberByGoodId(request.getParameter("goodmsg"));
            if (count > 0) {
                mav.addObject("status", 1000);
            } else {
                mav.addObject("status", 1001);
            }
        }
        mav.addObject("goodlist", goodlist);
        return mav;
    }

    @RequestMapping(value = "/gooddeallist", method = RequestMethod.GET)
    public ModelAndView gooddeallist(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("good/gooddeallist");
        return mav;
    }

    @RequestMapping(value = "/getgooddeallist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getgooddeallist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        String username = request.getParameter("keyword");
        List<GoodDeal> gooddeallist = goodDealService.getGoodDealList(username);

        resultmap.put("data", gooddeallist);
        resultmap.put("code", "1000");
        resultmap.put("msg", "ok");
        resultmap.put("code", "0");

        return resultmap;
    }

    @RequestMapping(value = "/getgoodchart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getgoodchart(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        List<Good> goodlist = new ArrayList<Good>();
        ArrayList<String> goodname = new ArrayList<String>();
        try {
            goodlist = goodSortService.getGood();
            int[] goodnumber = new int[goodlist.size()];
            for (int i = 0; i < goodlist.size(); i++) {
                goodname.add(goodlist.get(i).getGoodname());
                goodnumber[i] = goodlist.get(i).getGoodnumber();
            }
            map.put("goodname", goodname);
            map.put("goodnumber", goodnumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
