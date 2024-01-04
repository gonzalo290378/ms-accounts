package com.bench.msaccounts.exceptions;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private LocalDate timeStamp;
    private String message;
    private String detail;
}
