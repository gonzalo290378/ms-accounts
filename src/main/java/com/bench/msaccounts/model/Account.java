package com.bench.msaccounts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Type should have at least 2 characters")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Balance should have a number")
    @Column(name = "balance")
    private Double balance;

    @Column(name = "state")
    private Boolean state;

    @NotNull(message = "UserId should have an userId number")
    @Column(name = "user_id")
    private Long userId;

}
