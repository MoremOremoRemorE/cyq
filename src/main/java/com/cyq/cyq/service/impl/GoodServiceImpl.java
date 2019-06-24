package com.cyq.cyq.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cyq.cyq.mapper.GoodMapper;
import com.cyq.cyq.mapper.GoodSortMapper;
import com.cyq.cyq.model.Good;
import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.model.LayUITree;
import com.cyq.cyq.service.GoodService;
import com.cyq.cyq.service.GoodSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    public static final String LAYUI_PID = "-1";

    @Autowired
    private GoodSortMapper goodSortMapper;

    @Autowired
    private GoodMapper goodMapper;


    @Override
    public int checkName(String goodname) {
        return goodMapper.checkGoodName(goodname);
    }

    @Override
    public void addGood(Good good) {
         goodMapper.addGood(good);
    }

    @Override
    public void deleteGood(String goodid) {
        goodMapper.deleteGood(goodid);
    }

    @Override
    public void editGood(Good good) {
        goodMapper.editGood(good);
    }

    @Override
    public Good selectGoodByName(String goodname) {
       return  goodMapper.selectGoodByName(goodname);
    }

    @Override
    public List<Good> selectAllGood() {
        return goodMapper.selectAllGood();
    }

    @Override
    public int updateGoodNumberByGoodId(String goodid) {
        return goodMapper.updateGoodNumberByGoodId(goodid);
    }

    @Override
    public LayUITree getLayUITree() throws Exception {
        List<GoodSort> goodSortList = goodSortMapper.getGoodSortList();
        LayUITree layUITree = new LayUITree();
        if (CollectionUtils.isEmpty(goodSortList)) {
            return layUITree;
        }
        layUITree.setId(LAYUI_PID);
        layUITree.setPId("");
        layUITree.setSpread(false);
        layUITree.setName("分类");
        HashSet<String> ids = new HashSet<>();
        return this.fillingBeans(goodSortList, LAYUI_PID, layUITree, ids);
    }

    private LayUITree fillingBeans(List<GoodSort> goods, String pid, LayUITree layUITree, HashSet ids) {
        System.out.println("装配" + layUITree.getName());
        List<LayUITree> lists = new ArrayList<>();
        for (GoodSort goodSort : goods) {
            if ((!ids.contains(goodSort.getGoodsortid())) && StringUtils.equals(goodSort.getGoodsortpid(), pid)) {
                ids.add(goodSort.getGoodsortid());
                lists.add(fillingBeans(goods, goodSort.getGoodsortid(), goodSortTOLayUITree(goodSort), ids));
            }
        }
        layUITree.setChildren(lists);
        System.out.println("装配" + layUITree.getName() + "结束");
        return layUITree;
    }

    private LayUITree goodSortTOLayUITree(GoodSort goodSort) {
        LayUITree layUITree = new LayUITree();
        layUITree.setId(goodSort.getGoodsortid());
        layUITree.setPId(goodSort.getGoodsortpid());
        layUITree.setName(goodSort.getGoodsortname());
        layUITree.setSpread(true);
        layUITree.setStatus(goodSort.getGoodsortstatus());
        layUITree.setCreatTime(goodSort.getCreattime());
        layUITree.setUpdateTime(goodSort.getUpdatetime());
        return layUITree;
    }
}
