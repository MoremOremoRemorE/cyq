package com.cyq.cyq.model;

import org.springframework.stereotype.Component;

@Component
public class Good {
    private String goodid;
    private String goodname;
    private String goodsortid;
    private String price;
    private String memprice;
    private String address;
    private String status;
    private String rukutime;
    private int goodnumber;

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoodsortid() {
        return goodsortid;
    }

    public void setGoodsortid(String goodsortid) {
        this.goodsortid = goodsortid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMemprice() {
        return memprice;
    }

    public void setMemprice(String memprice) {
        this.memprice = memprice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRukutime() {
        return rukutime;
    }

    public void setRukutime(String rukutime) {
        this.rukutime = rukutime;
    }

    public int getGoodnumber() {
        return goodnumber;
    }

    public void setGoodnumber(int goodnumber) {
        this.goodnumber = goodnumber;
    }
}
