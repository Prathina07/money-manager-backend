package com.prathina.money_manager.repository;

import com.prathina.money_manager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // ✅ Get all expenses for a specific user
    List<Expense> findByUserId(Long userId);

    // ✅ Optional: get by type (Income / Expense)
    List<Expense> findByUserIdAndType(Long userId, String type);

    // ✅ Optional: get by category
    List<Expense> findByUserIdAndCategory(Long userId, String category);
}