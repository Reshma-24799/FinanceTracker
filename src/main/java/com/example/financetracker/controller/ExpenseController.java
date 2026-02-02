package com.example.financetracker.controller;

import com.example.financetracker.model.Expense;
import com.example.financetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Expense> createExpense(@PathVariable Long userId, @Valid @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.createExpense(userId, expense));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getExpensesByUserId(userId));
    }

    @GetMapping("/total/user/{userId}")
    public ResponseEntity<BigDecimal> getTotalByCategory(@PathVariable Long userId, @RequestParam String category) {
        return ResponseEntity.ok(expenseService.getTotalByCategory(userId, category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}
