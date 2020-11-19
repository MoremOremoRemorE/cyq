package com.cyq.cyq.service;

import java.security.GeneralSecurityException;

public interface SendEmailService {

    public void sendEmail(String username,String email,String keyword) throws GeneralSecurityException;
    public void sendEmaileditpassword(String username,String email,String password) throws GeneralSecurityException;

}
