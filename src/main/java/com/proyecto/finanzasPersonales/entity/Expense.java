package com.proyecto.finanzasPersonales.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fp_expense")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
