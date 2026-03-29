package com.prathina.money_manager.controller;

import com.prathina.money_manager.model.Expense;
import com.prathina.money_manager.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins="http://localhost:5173")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // GET ALL EXPENSES
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // ADD EXPENSE
   @PostMapping
public Expense addExpense(@RequestBody Expense expense) {
    return expenseRepository.save(expense);
}

    // DELETE EXPENSE
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }
}