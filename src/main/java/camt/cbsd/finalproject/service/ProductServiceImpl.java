package camt.cbsd.finalproject.service;

import camt.cbsd.finalproject.dao.ProductDao;
import camt.cbsd.finalproject.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@ConfigurationProperties(prefix = "server")
@Service
public class ProductServiceImpl implements ProductService {
    String imageBaseUrl;
    String baseUrl;
    String imageUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    @PostConstruct
    protected void setImageBaseUrl(){
        this.imageBaseUrl = this.baseUrl + this.imageUrl;
    }

    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    @Transactional
    public Product findById(long id) {
        Product product= productDao.findById(id);
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    @Transactional
    public List<Product> queryProduct(String query) {
        if (query==null || query.equals("")){
            return productDao.getProducts();
        }
        return  productDao.getProducts(query);
    }
    @Override
    public Product updateProduct(Product product) {
        return productDao.updateProduct(product);
    }


}
