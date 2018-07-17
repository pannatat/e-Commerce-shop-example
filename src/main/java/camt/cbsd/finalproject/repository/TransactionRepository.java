package camt.cbsd.finalproject.repository;

import camt.cbsd.finalproject.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TransactionRepository extends CrudRepository<Transaction,Long>{
    List<Transaction> findByDate(String date);
    Transaction findById(long id);
    List<Transaction> findByDateOrPayment(String date, String payment);
}
