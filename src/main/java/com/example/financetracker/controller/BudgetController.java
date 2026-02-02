package com.example.financetracker.controller;

import com.example.financetracker.model.Budget;
import com.example.financetracker.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Budget> setBudget(@PathVariable Long userId, @Valid @RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.setBudget(userId, budget));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long userId, @Valid @RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.updateBudget(userId, budget));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long userId, @RequestParam String category) {
        budgetService.deleteBudget(userId, category);
        return ResponseEntity.ok().build();
    }
}
