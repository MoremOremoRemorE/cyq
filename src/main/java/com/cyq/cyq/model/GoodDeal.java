package com.cyq.cyq.model;

import org.springframework.stereotype.Component;

@Component
public class GoodDeal {
    private String id;
    private String  userid;
    private String username;
    private String date;
    private String phone;
    private String address;
    private String goodid;
    private String goodname;
    private int gohomeflag;
    private int fenqiflag;
    private int fapiaoflag;
    private String note;
    private String content;
    private String filepath;
    private String operatorname;
    private String operatorid;
    private String price;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public int getGohomeflag() {
        return gohomeflag;
    }

    public void setGohomeflag(int gohomeflag) {
        this.gohomeflag = gohomeflag;
    }

    public int getFenqiflag() {
        return fenqiflag;
    }

    public void setFenqiflag(int fenqiflag) {
        this.fenqiflag = fenqiflag;
    }

    public int getFapiaoflag() {
        return fapiaoflag;
    }

    public void setFapiaoflag(int fapiaoflag) {
        this.fapiaoflag = fapiaoflag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getOperatorname() {
        return operatorname;
    }

    public void setOperatorname(String operatorname) {
        this.operatorname = operatorname;
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
