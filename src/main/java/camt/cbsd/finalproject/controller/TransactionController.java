package camt.cbsd.finalproject.controller;

import camt.cbsd.finalproject.entity.Transaction;
import camt.cbsd.finalproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class TransactionController {
    TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @CrossOrigin
    @GetMapping("/transaction")
    public ResponseEntity getTransactions(){
        List<Transaction> transactions = transactionService.getTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("transactions")
    public ResponseEntity<?> queryProduct(@RequestParam("search") String query) {
        List<Transaction> transactions = transactionService.queryTransaction(query);
        if (transactions != null)
            return ResponseEntity.ok(transactions);
        else
            //http code 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("transaction/{id}")
    public ResponseEntity getTransaction(@PathVariable("id")long id){
        Transaction transaction = transactionService.findById(id);
        if(transaction!=null){
            return ResponseEntity.ok(transaction);
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
