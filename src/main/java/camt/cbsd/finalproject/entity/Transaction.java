package camt.cbsd.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String date;
    String payment;
    @ManyToMany
    List<Product> productList;

    public List<Product> addProduct(Product product){
        productList = Optional.ofNullable(productList).orElse(new ArrayList<>());
        productList.add(product);
        return productList;
    }
}
