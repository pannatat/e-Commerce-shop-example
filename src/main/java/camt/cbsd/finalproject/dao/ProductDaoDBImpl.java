package camt.cbsd.finalproject.dao;

import camt.cbsd.finalproject.entity.Product;
import camt.cbsd.finalproject.repository.ProductRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("DBDataSource")
@Repository
public class ProductDaoDBImpl implements ProductDao {
    ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return Lists.newArrayList(productRepository.findAll());
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Integer size() {
        return (int)productRepository.count();
    }

    @Override
    public List<Product> getProducts(String searchText){
        return productRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(searchText,searchText);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

}
