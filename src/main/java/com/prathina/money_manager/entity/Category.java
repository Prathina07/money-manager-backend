package com.prathina.money_manager.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.prathina.money_manager.model.User;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}