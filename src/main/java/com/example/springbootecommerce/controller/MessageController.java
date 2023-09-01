package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.pojo.entity.Message;
import com.example.springbootecommerce.pojo.entity.Portfolio;
import com.example.springbootecommerce.pojo.requests.MessageRequest;
import com.example.springbootecommerce.pojo.requests.PortfolioRequest;
import com.example.springbootecommerce.pojo.responses.MessagePageResponse;
import com.example.springbootecommerce.pojo.responses.ObjectResponse;
import com.example.springbootecommerce.pojo.responses.PortfolioPageResponse;
import com.example.springbootecommerce.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@CrossOrigin(maxAge = 3600)
public class MessageController {
    @Autowired
    private MessageService messageService;
    @PostMapping("/save")
    public ResponseEntity<ObjectResponse> saveMessage(@RequestBody MessageRequest request){
        Message message = messageService.createMessage(request);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.CREATED,"Create Message successfully",message)
        );
    }
    @GetMapping("")
    public ResponseEntity<ObjectResponse> getPortfolioPage(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        MessagePageResponse messagePageResponse = messageService.listMessage(page, limit);
        return ResponseEntity.ok().body(
                new ObjectResponse(HttpStatus.OK,"Get List Message Successfully",messagePageResponse)
        );
    }
}
