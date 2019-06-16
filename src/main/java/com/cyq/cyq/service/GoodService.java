package com.cyq.cyq.service;


import com.cyq.cyq.model.Good;

import java.util.List;

public interface GoodService {

    public int checkName(String goodname);

    public void addGood(Good good);

    public void deleteGood(String goodid);

    public void editGood(Good good);

    public Good selectGoodByName(String goodname);

    List<Good> selectAllGood();
}
