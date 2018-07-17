package camt.cbsd.finalproject.config;

import camt.cbsd.finalproject.dao.ActorDao;
import camt.cbsd.finalproject.dao.ProductDao;
import camt.cbsd.finalproject.dao.TransactionDao;
import camt.cbsd.finalproject.entity.Actor;
import camt.cbsd.finalproject.entity.Product;
import camt.cbsd.finalproject.entity.Transaction;
import camt.cbsd.finalproject.entity.security.Authority;
import camt.cbsd.finalproject.entity.security.AuthorityName;
import camt.cbsd.finalproject.entity.security.User;
import camt.cbsd.finalproject.security.repository.AuthorityRepository;
import camt.cbsd.finalproject.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ConfigurationProperties(prefix = "server")
@Component
public class DataLoader implements ApplicationRunner{
    ProductDao productDao;
    TransactionDao transactionDao;
    ActorDao actorDao;

    @Autowired
    public void setActorDao(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Autowired
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    UserRepository userRepository;

    AuthorityRepository authorityRepository;

    String baseUrl;
    String imageUrl;
    String imageBaseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        imageBaseUrl = baseUrl + imageUrl;
        Product product1=Product.builder().productId("G-001").name("Dota 2")
                .description("Defense of the Ancients 2")
                .price(159).image(imageBaseUrl+"dota.jpg").show(true).clicked(20).build();

        Product product2=Product.builder().productId("G-002").name("CSGO")
                .description("Counter Strike Global Offensive")
                .price(699).image(imageBaseUrl+"csgo.png").show(true).clicked(150).build();

        Product product3=Product.builder().productId("G-003").name("Left 4 Dead")
                .description("The most famous zombies game that you can MOD")
                .price(300).image(imageBaseUrl+"l4d.jpg").show(true).clicked(30).build();

        Product product4=Product.builder().productId("G-003").name("PUBG")
                .description("Player Unknown's Battle Ground")
                .price(599).image(imageBaseUrl+"pubg.jpg").show(true).clicked(30).build();

        productDao.addProduct(product1);
        productDao.addProduct(product2);
        productDao.addProduct(product3);
        productDao.addProduct(product4);

        Transaction transaction1 = Transaction.builder().date("11-11-2015").payment("slip").build();
        transactionDao.addTransaction(transaction1);
        transaction1.addProduct(product1);
        transaction1.addProduct(product2);


        Transaction transaction2 = Transaction.builder().date("08-09-2017").payment("paypal").build();
        transactionDao.addTransaction(transaction2);
        transaction2.addProduct(product1);



        Actor actor1=Actor.builder().actorId("A-001").name("Porring").build();
        Actor actor2=Actor.builder().actorId("A-002").name("Abe").build();
        Actor actor3=Actor.builder().actorId("A-003").name("Tanee").build();


        actorDao.addActor(actor1);
        actorDao.addActor(actor2);
        actorDao.addActor(actor3);


        securitySetup();
        actor1.setUser(user1);
        user1.setActor(actor1);
        actor2.setUser(user2);
        user2.setActor(actor2);
        actor3.setUser(user3);
        user3.setActor(actor3);

    }

    User user1, user2, user3;
    public void securitySetup(){
        user1 = User.builder()
                .username("admin")
                .password("admin")
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2016,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("shopkeeper")
                .password("shopkeeper")
                .firstname("shopkeeper")
                .lastname("shopkeeper")
                .email("shopkeeper@shopkeeper.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2016,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user3 = User.builder()
                .username("customer")
                .password("customer")
                .firstname("customer")
                .lastname("customer")
                .email("customer@customer.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2016,01,01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        Authority auth1 = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority auth2 = Authority.builder().name(AuthorityName.ROLE_SHOPKEEPER).build();
        Authority auth3 = Authority.builder().name(AuthorityName.ROLE_CUSTOMER).build();
        Authority auth4 = Authority.builder().name(AuthorityName.ROLE_USER).build();



        authorityRepository.save(auth1);
        authorityRepository.save(auth2);
        authorityRepository.save(auth3);
        authorityRepository.save(auth4);

        user1.setAuthorities(new ArrayList<>());
        user1.getAuthorities().add(auth1);
        user2.setAuthorities(new ArrayList<>());
        user2.getAuthorities().add(auth2);
        user3.setAuthorities(new ArrayList<>());
        user3.getAuthorities().add(auth3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);




    }



}
