package camt.cbsd.finalproject.dao;

import camt.cbsd.finalproject.entity.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> getTransactions();
    List<Transaction> getTransactions(String searchText);
    Transaction addTransaction(Transaction transaction);
    Transaction findById(long id);
}
