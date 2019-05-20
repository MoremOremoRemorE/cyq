package com.cyq.cyq.mapper;

import com.cyq.cyq.model.Good;
import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodSortMapper {

    public List<GoodSort> getGoodSortList() throws Exception;

    public void deleteGoodSort(String goodsortid);

    public GoodSort getGoodSortByName(String goodsortname);

    public void editGoodSort(GoodSort nwegoodSort);

    public int checkName(@Param("goodsortname")String goodsortname);

    public void addGoodSort(GoodSort newgoodSort);

    public List<Good> getGood() throws Exception;
}
