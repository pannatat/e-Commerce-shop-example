package camt.cbsd.finalproject.dao;

import camt.cbsd.finalproject.entity.Transaction;
import camt.cbsd.finalproject.repository.TransactionRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("DBDataSource")
@Repository
public class TransactionDaoDBImpl implements TransactionDao{

    TransactionRepository transactionRepository;
    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getTransactions() {
        return Lists.newArrayList(transactionRepository.findAll());
    }

    @Override
    public List<Transaction> getTransactions(String searchText){
        return transactionRepository.findByDateOrPayment(searchText,searchText);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(long id) {
        return transactionRepository.findById(id);
    }
}
