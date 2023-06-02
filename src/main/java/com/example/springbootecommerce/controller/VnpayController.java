package com.example.springbootecommerce.controller;


import com.example.springbootecommerce.config.VnpayConfig;
import com.example.springbootecommerce.pojo.requests.VnpayRequest;
import com.example.springbootecommerce.pojo.responses.PaymentResponse;
import com.example.springbootecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class VnpayController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/vnpay")
    public ResponseEntity<PaymentResponse> createVnpay(@RequestBody VnpayRequest vnpayRequest) throws UnsupportedEncodingException {
        PaymentResponse paymentResponse = paymentService.createVnpay(vnpayRequest);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResponse);
    }

}
