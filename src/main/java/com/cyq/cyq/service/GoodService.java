package com.cyq.cyq.service;

import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.model.User;

import java.util.List;

public interface GoodService {

    public List<GoodSort> getGoodSortList()throws Exception;
}
