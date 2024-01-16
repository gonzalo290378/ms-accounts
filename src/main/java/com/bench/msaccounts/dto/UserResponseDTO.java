package com.bench.msaccounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserResponseDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("dni")
    private String dni;
}
