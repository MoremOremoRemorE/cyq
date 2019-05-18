package com.cyq.cyq.service.impl;

import com.cyq.cyq.mapper.GoodMapper;
import com.cyq.cyq.mapper.GoodSortMapper;
import com.cyq.cyq.model.Good;
import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.service.GoodService;
import com.cyq.cyq.service.GoodSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
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
}
