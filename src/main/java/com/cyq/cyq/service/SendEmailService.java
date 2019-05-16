package com.cyq.cyq.service;

import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

public interface SendEmailService {

    public void sendEmail(String username,String email,String keyword) throws GeneralSecurityException;
    public void senfEmaileditpassword(String username,String email,String password) throws GeneralSecurityException;

}
