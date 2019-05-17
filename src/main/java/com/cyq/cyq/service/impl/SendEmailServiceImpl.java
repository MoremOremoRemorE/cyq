package com.cyq.cyq.service.impl;

import com.cyq.cyq.service.SendEmailService;
import com.cyq.cyq.utils.SendEmail;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Override
    public void sendEmail(String username,String email,String keyword) throws GeneralSecurityException {
        SendEmail.senfEmail(username,email,keyword);
    }

    @Override
    public void sendEmaileditpassword(String username, String email, String password) throws GeneralSecurityException {
        SendEmail.sendEmaileditpassword(username,email,password);
    }
}
