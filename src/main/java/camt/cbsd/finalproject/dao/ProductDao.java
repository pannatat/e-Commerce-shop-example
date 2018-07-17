package camt.cbsd.finalproject.dao;

import camt.cbsd.finalproject.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
    Product findById(long id);
    Product addProduct(Product product);
    Integer size();
    List<Product> getProducts(String searchText);
    Product updateProduct(Product product);

}
