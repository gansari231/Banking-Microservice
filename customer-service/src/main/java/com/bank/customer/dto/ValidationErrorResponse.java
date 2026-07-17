package com.bank.customer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ValidationErrorResponse{

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private List<String> messages;

}
