package com.prathina.money_manager.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@JsonFormat(pattern = "yyyy-MM-dd")
@Table(name="expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double amount;
    private String type;
    private String category;
    private LocalDate date;
    // LINK EXPENSE TO USER
    private Long userId;

}