package com.cyq.cyq.mapper;

import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodMapper {

    public List<GoodSort> getGoodSortList() throws Exception;

    public void deleteGoodSort(String goodsortid);
}
