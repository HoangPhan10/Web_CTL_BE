package com.example.springbootecommerce.service;

import com.example.springbootecommerce.pojo.requests.VnpayRequest;
import com.example.springbootecommerce.pojo.responses.PaymentResponse;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    PaymentResponse createVnpay(VnpayRequest vnpayRequest) throws UnsupportedEncodingException;
}
