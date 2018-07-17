package camt.cbsd.finalproject.repository;

import camt.cbsd.finalproject.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findById(long id);
    List<Product> findByName(String name);
    List<Product> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String name,String description);
}
