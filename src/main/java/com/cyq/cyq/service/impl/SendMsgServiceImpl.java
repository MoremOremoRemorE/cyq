package com.cyq.cyq.service.impl;

import com.cyq.cyq.service.SendMsgService;
import com.cyq.cyq.utils.SendMsg;
import org.springframework.stereotype.Service;

@Service
public class SendMsgServiceImpl implements SendMsgService {

    @Override
    public void sendMsg() throws Exception {
        SendMsg.sendMsg();
    }
}
