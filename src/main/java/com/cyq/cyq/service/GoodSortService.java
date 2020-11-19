package com.cyq.cyq.service;

import com.cyq.cyq.model.Good;
import com.cyq.cyq.model.GoodSort;

import java.util.List;

public interface GoodSortService {

    public List<GoodSort> getGoodSortList()throws Exception;

    public void deleteGoodSort(String goodsortid) throws Exception;

    public GoodSort getGoodSortByName(String goodsortname);

    public void editGoodSort(GoodSort nwegoodSort);

    public int checkName(String goodsortname);

    public void addGoodSort(GoodSort newgoodSort);

    public List<Good> getGood()throws Exception;
}
