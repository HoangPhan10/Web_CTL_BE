package com.example.springbootecommerce.pojo.requests;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequest {
    @NotNull(message = "Name not null")
    private String name;
    @NotNull(message = "id_user not null")
    private Long id_user;
    @NotNull(message = "id_address not null")
    private Long id_address;
    //check id_user va id_add ton tai
}
