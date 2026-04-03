package com.prathina.money_manager.controller;

import com.prathina.money_manager.model.Expense;
import com.prathina.money_manager.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // ✅ GET ALL (optional - for testing)
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // ✅ GET EXPENSES BY USER (IMPORTANT FIX)
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getExpensesByUser(@PathVariable Long userId) {
        try {
            List<Expense> expenses = expenseRepository.findByUserId(userId);
            return ResponseEntity.ok(expenses);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching expenses");
        }
    }

    // ✅ ADD EXPENSE
    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
        try {
            return ResponseEntity.ok(expenseRepository.save(expense));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error adding expense");
        }
    }

    // ✅ UPDATE EXPENSE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        try {
            Expense existing = expenseRepository.findById(id).orElse(null);

            if (existing == null) {
                return ResponseEntity.status(404).body("Expense not found");
            }

            expense.setId(id);
            return ResponseEntity.ok(expenseRepository.save(expense));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating expense");
        }
    }

    // ✅ DELETE EXPENSE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            if (!expenseRepository.existsById(id)) {
                return ResponseEntity.status(404).body("Expense not found");
            }

            expenseRepository.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting expense");
        }
    }
}