package com.proyecto.finanzasPersonales.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestExpenseDTO {
    private Double amount;
    private LocalDate date;
    private Long category;
    private String description;
    private Long user;
}
