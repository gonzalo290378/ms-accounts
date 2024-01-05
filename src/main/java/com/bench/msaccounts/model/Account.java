package com.bench.msaccounts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

    //@Size(min=5, message = "Type should have at least 2 characters (CA - CC)")
    @Column(name = "type")
    private char type;

    //@Size(min=5, message = "Balance ")
    @Column(name = "balance")
    private Double balance;

    //@Email(message = "Please provide a valid email address")
    //@NotEmpty(message = "Email cannot be empty")
    @Column(name = "state")
    private Boolean state;

}
