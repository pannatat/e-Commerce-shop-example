package camt.cbsd.finalproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    long id;
    @NonNull
    String productId;
    @NonNull
    String name;
    @NonNull
    String description;
    @NonNull
    double price;
    @NonNull
    String image;
    @NonNull
    int amount;
    @NonNull
    boolean show = true;
    @NotNull
    boolean selected = false;
    long clicked;


}
