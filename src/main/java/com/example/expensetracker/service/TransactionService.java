
package com.example.expensetracker.service;

import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repo;

    public Transaction addTransaction(Transaction tx) {
        return repo.save(tx);
    }

    public List<Transaction> getAll() {
        return repo.findAll();
    }

    public Map<String, Double> getSummary() {
        List<Transaction> list = repo.findAll();
        double income = list.stream().filter(t -> "income".equals(t.getType()))
                            .mapToDouble(Transaction::getAmount).sum();
        double expense = list.stream().filter(t -> "expense".equals(t.getType()))
                             .mapToDouble(Transaction::getAmount).sum();
        double balance = income - expense;
        return Map.of("totalIncome", income, "totalExpense", expense, "balance", balance);
    }
}
