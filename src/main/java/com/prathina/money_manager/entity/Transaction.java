package com.prathina.money_manager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.prathina.money_manager.model.User;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String description;
    private LocalDate date;
    private String type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}