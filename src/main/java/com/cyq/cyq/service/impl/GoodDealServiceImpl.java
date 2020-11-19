package com.cyq.cyq.service.impl;

import com.cyq.cyq.mapper.GoodDealMapper;
import com.cyq.cyq.model.GoodDeal;
import com.cyq.cyq.service.GoodDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodDealServiceImpl implements GoodDealService {

    @Autowired
    private GoodDealMapper goodDealMapper;

    @Override
    public int insertGoodDealInfo(GoodDeal goodDeal) {
        return goodDealMapper.insertGoodDealInfo(goodDeal);
    }

    @Override
    public List<GoodDeal> getGoodDealList(String username) {
        return goodDealMapper.getGoodDealList(username);
    }
}
