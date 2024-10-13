package com.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String userId;
    private String name;
    private String email;
    private String address;
    private String phone;
}
