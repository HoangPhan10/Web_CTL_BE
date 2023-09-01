package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.entity.Message;
import com.example.springbootecommerce.pojo.requests.MessageRequest;
import com.example.springbootecommerce.pojo.responses.MessagePageResponse;

public interface MessageService {
    Message createMessage(MessageRequest messageRequest);
    MessagePageResponse listMessage(int page,int limit);
}
