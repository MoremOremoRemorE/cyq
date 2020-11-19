package com.cyq.cyq.model;

import org.springframework.stereotype.Component;

@Component
public class GoodSort {
    private String goodsortid;
    private String goodsortname;
    private String goodsortpid;
    private String goodsortstatus;
    private String creattime;
    private String updatetime;


    public String getGoodsortpid() {
        return goodsortpid;
    }

    public void setGoodsortpid(String goodsortpid) {
        this.goodsortpid = goodsortpid;
    }

    public String getGoodsortstatus() {
        return goodsortstatus;
    }

    public void setGoodsortstatus(String goodsortstatus) {
        this.goodsortstatus = goodsortstatus;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getGoodsortid() {
        return goodsortid;
    }

    public void setGoodsortid(String goodsortid) {
        this.goodsortid = goodsortid;
    }

    public String getGoodsortname() {
        return goodsortname;
    }

    public void setGoodsortname(String goodsortname) {
        this.goodsortname = goodsortname;
    }
}
