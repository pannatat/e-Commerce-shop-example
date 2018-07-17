package camt.cbsd.finalproject.service;

import camt.cbsd.finalproject.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    List<Transaction> queryTransaction(String query);
    Transaction findById(long id);
}
