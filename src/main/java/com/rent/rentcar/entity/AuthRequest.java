package com.rent.rentcar.entity;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

}