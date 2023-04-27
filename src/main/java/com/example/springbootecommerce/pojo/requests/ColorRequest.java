package com.example.springbootecommerce.pojo.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorRequest {
    @NotNull(message = "Color not null")
    private String color;
    @NotNull(message = "id_product not null")
    private Long id_product;
}