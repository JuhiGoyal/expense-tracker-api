
package com.example.expensetracker.controller;

import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/income")
    public Transaction addIncome(@RequestBody Transaction tx) {
        tx.setType("income");
        return service.addTransaction(tx);
    }

    @PostMapping("/expense")
    public Transaction addExpense(@RequestBody Transaction tx) {
        tx.setType("expense");
        return service.addTransaction(tx);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAll() {
        return service.getAll();
    }

    @GetMapping("/summary")
    public Map<String, Double> getSummary() {
        return service.getSummary();
    }
}
