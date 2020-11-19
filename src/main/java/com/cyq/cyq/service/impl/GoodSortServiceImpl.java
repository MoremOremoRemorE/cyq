package com.cyq.cyq.service.impl;

import com.cyq.cyq.mapper.GoodSortMapper;
import com.cyq.cyq.model.Good;
import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.service.GoodSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodSortServiceImpl implements GoodSortService {
    @Autowired
    private GoodSortMapper goodSortMapper;
    @Override
    public List<GoodSort> getGoodSortList() throws Exception {
        return goodSortMapper.getGoodSortList();
    }

    @Override
    public void deleteGoodSort(String goodsortid) throws Exception {
        goodSortMapper.deleteGoodSort(goodsortid);
    }

    @Override
    public GoodSort getGoodSortByName(String goodsortname) {
        return goodSortMapper.getGoodSortByName(goodsortname);
    }

    @Override
    public void editGoodSort(GoodSort nwegoodSort) {
        goodSortMapper.editGoodSort(nwegoodSort);
    }

    @Override
    public int checkName(String goodsortname) {
        return goodSortMapper.checkName(goodsortname);
    }

    @Override
    public void addGoodSort(GoodSort newgoodSort) {
        goodSortMapper.addGoodSort(newgoodSort);
    }

    @Override
    public List<Good> getGood() throws Exception {
        return goodSortMapper.getGood();
    }

}
