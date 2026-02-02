package com.example.financetracker.service;

import com.example.financetracker.model.Expense;
import com.example.financetracker.model.User;
import com.example.financetracker.repository.ExpenseRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.example.financetracker.repository.BudgetRepository budgetRepository;

    public Expense createExpense(Long userId, Expense expense) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Budget Check
        java.util.Optional<com.example.financetracker.model.Budget> budgetOpt = budgetRepository
                .findByUserIdAndCategory(userId, expense.getCategory());

        if (budgetOpt.isPresent()) {
            BigDecimal currentTotal = getTotalByCategory(userId, expense.getCategory());
            BigDecimal newTotal = currentTotal.add(expense.getAmount());
            if (newTotal.compareTo(budgetOpt.get().getLimitAmount()) > 0) {
                throw new com.example.financetracker.exception.BudgetExceededException(
                        "Budget exceeded for category: " + expense.getCategory() +
                                ". Limit: " + budgetOpt.get().getLimitAmount() +
                                ", Current Total: " + currentTotal +
                                ", New Expense: " + expense.getAmount());
            }
        }

        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDate(expenseDetails.getDate());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        expenseRepository.delete(expense);
    }

    public BigDecimal getTotalByCategory(Long userId, String category) {
        BigDecimal total = expenseRepository.calculateTotalByCategory(category, userId);
        return total != null ? total : BigDecimal.ZERO;
    }
}
