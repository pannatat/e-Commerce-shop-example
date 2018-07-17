package camt.cbsd.finalproject.service;

import camt.cbsd.finalproject.dao.TransactionDao;
import camt.cbsd.finalproject.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@ConfigurationProperties(prefix = "server")
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDao transactionDao;

    @Override
    public List<Transaction> getTransactions() {
        return transactionDao.getTransactions();
    }

    @Override
    @Transactional
    public List<Transaction> queryTransaction(String query) {
        if (query==null || query.equals("")){
            return transactionDao.getTransactions();
        }
        return  transactionDao.getTransactions(query);
    }

    @Override
    public Transaction findById(long id) {
        return transactionDao.findById(id);
    }
}
