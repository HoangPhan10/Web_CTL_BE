package com.example.springbootecommerce.pojo.responses;

import com.example.springbootecommerce.pojo.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePageResponse {
    private List<Message> messageResponses;
    private Integer total;
    private Long totalElement;
}
