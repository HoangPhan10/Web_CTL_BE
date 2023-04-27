package com.example.springbootecommerce.pojo.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @NotNull(message = "comment not null")
    private String comment;
    @NotNull(message = "rate not null")
    private int rate;
    @NotNull(message = "id_product not null")
    private Long id_product;
    @NotNull(message = "id_user not null")
    private Long id_user;
}
