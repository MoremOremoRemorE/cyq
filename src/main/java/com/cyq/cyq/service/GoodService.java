package com.cyq.cyq.service;

import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.model.User;

import java.util.List;

public interface GoodService {

    public List<GoodSort> getGoodSortList()throws Exception;

    public void deleteGoodSort(String goodsortid) throws Exception;

    public GoodSort getGoodSortByName(String goodsortname);

    public void editGoodSort(GoodSort nwegoodSort);

    public int checkName(String goodsortname);

    public void addGoodSort(GoodSort newgoodSort);
}
