package com.example.springbootecommerce.service.implement;

import com.example.springbootecommerce.pojo.entity.Message;
import com.example.springbootecommerce.pojo.entity.Portfolio;
import com.example.springbootecommerce.pojo.requests.MessageRequest;
import com.example.springbootecommerce.pojo.responses.MessagePageResponse;
import com.example.springbootecommerce.repository.MessageRepository;
import com.example.springbootecommerce.service.MessageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageImplementService implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message createMessage(MessageRequest messageRequest) {
        Message message = new Message();
        message.setName(messageRequest.getName());
        message.setEmail(messageRequest.getEmail());
        message.setPhone(messageRequest.getPhone());
        message.setContent(messageRequest.getContent());
        return messageRepository.save(message);
    }

    @Override
    public MessagePageResponse listMessage(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        Page<Message> messages = messageRepository.findAll(pageable);
        List<Message> messageList = new ArrayList<>();
        for(Message message : messages) {
            messageList.add(message);
        }
        Integer totalPage = messages.getTotalPages();
        Long totalElement = messages.getTotalElements();
        MessagePageResponse messagePageResponse = new MessagePageResponse();
        messagePageResponse.setTotalElement(totalElement);
        messagePageResponse.setTotal(totalPage);
        messagePageResponse.setMessageResponses(messageList);
        return messagePageResponse;
    }
}
