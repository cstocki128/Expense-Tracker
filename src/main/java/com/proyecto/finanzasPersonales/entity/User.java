package com.proyecto.finanzasPersonales.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "FP_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;
}
