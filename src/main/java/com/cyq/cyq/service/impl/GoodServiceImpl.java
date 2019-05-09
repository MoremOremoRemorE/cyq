package com.cyq.cyq.service.impl;

import com.cyq.cyq.mapper.GoodMapper;
import com.cyq.cyq.mapper.UserMapper;
import com.cyq.cyq.model.GoodSort;
import com.cyq.cyq.model.User;
import com.cyq.cyq.service.GoodService;
import com.cyq.cyq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper ;
    @Override
    public List<GoodSort> getGoodSortList() throws Exception {
        return goodMapper.getGoodSortList();
    }

}
