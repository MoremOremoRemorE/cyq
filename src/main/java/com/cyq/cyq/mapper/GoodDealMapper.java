package com.cyq.cyq.mapper;

import com.cyq.cyq.model.GoodDeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodDealMapper {
    int insertGoodDealInfo(GoodDeal goodDeal);

    List<GoodDeal> getGoodDealList( @Param("username") String username);
}
