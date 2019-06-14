package com.cyq.cyq.model;

import org.springframework.stereotype.Component;

@Component
public class GoodDeal {
    private String gooddealid;
    private String fromusername;
    private String tousername;
    private String dealaddress;
    private String dealtime;
    private String dealgoodid;
    private String goodname;

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getDealgoodid() {
        return dealgoodid;
    }

    public void setDealgoodid(String dealgoodid) {
        this.dealgoodid = dealgoodid;
    }

    public String getGooddealid() {
        return gooddealid;
    }

    public void setGooddealid(String gooddealid) {
        this.gooddealid = gooddealid;
    }

    public String getFromusername() {
        return fromusername;
    }

    public void setFromusername(String fromusername) {
        this.fromusername = fromusername;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public String getDealaddress() {
        return dealaddress;
    }

    public void setDealaddress(String dealaddress) {
        this.dealaddress = dealaddress;
    }

    public String getDealtime() {
        return dealtime;
    }

    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }
}
