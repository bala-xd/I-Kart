package com.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerDTO {
    private String userId;
    private String name;
    private String email;
    private LocalDate dob;
    private String address;
    private String phone;
    private String password;
    private int loginStatus;
}
