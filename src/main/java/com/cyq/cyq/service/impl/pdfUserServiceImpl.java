package com.cyq.cyq.service.impl;

import com.cyq.cyq.mapper.pdfUserMapper;
import com.cyq.cyq.model.pdfUser;
import com.cyq.cyq.service.pdfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class pdfUserServiceImpl implements pdfUserService {
    @Autowired
    private pdfUserMapper pdfuserMapper;

    @Override
    public int insertpdf(pdfUser pdfUser) {
        return pdfuserMapper.insertpdf(pdfUser);
    }
}
