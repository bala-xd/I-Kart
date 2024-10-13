package com.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Customer {
    @Id
    private String userId;

    private String name;
    private String email;
    private LocalDate dob;
    private String address;
    private String phone;
    private String password;
    private int loginStatus;
}