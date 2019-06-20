package com.cyq.cyq.service;

import com.cyq.cyq.model.GoodDeal;

import java.util.List;

public interface GoodDealService {

    int insertGoodDealInfo(GoodDeal goodDeal);

    List<GoodDeal> getGoodDealList(String username);
}
