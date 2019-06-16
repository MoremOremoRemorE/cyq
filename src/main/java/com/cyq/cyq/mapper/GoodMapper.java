package com.cyq.cyq.mapper;

import com.cyq.cyq.model.Good;
import com.cyq.cyq.model.GoodDeal;
import com.cyq.cyq.model.GoodSort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodMapper {

    public int checkGoodName(String goodname);

    public void addGood(Good good);

    public void deleteGood(String goodid);

    public  void editGood(Good good);

    public Good selectGoodByName(String goodname);

    public List<GoodDeal> getGoodDeal();

    List<Good> selectAllGood();
}
