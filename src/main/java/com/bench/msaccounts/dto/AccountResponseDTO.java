package com.bench.msaccounts.dto;

import com.bench.msaccounts.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private char type;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("state")
    private Boolean state;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("userResponseDTO")
    private User user;

}