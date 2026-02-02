package com.example.financetracker.service;

import com.example.financetracker.model.Budget;
import com.example.financetracker.model.User;
import com.example.financetracker.repository.BudgetRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    public Budget setBudget(Long userId, Budget budget) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Budget> existingBudget = budgetRepository.findByUserIdAndCategory(userId, budget.getCategory());

        if (existingBudget.isPresent()) {
            Budget current = existingBudget.get();
            current.setLimitAmount(budget.getLimitAmount());
            return budgetRepository.save(current);
        } else {
            budget.setUser(user);
            return budgetRepository.save(budget);
        }
    }

    public Budget updateBudget(Long userId, Budget budget) {
        return setBudget(userId, budget); // Logic handles update if exists
    }

    public void deleteBudget(Long userId, String category) {
        Budget budget = budgetRepository.findByUserIdAndCategory(userId, category)
                .orElseThrow(() -> new RuntimeException("Budget not found for category: " + category));
        budgetRepository.delete(budget);
    }
}
