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
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=5, message = "Username should have at least 5 characters")
    @Column(name = "username")
    private String username;

    @Size(min=5, message = "Password should have at least 5 characters")
    @Column(name = "password")
    private String password;

    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;

    @Size(min = 7, max = 8, message = "Dni must have 7 to 8 characters")
    @Column(name = "dni")
    private String dni;

}
