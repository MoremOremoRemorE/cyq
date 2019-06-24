package com.cyq.cyq.model;

import org.springframework.stereotype.Component;

@Component
public class CustomerInfo {
    private String  userid;
    private String username;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
