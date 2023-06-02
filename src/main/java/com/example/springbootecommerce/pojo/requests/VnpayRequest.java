package com.example.springbootecommerce.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VnpayRequest {
    private Long order_id;
    private String content_pay;
    private Long amount;
    private Long callback;
}
