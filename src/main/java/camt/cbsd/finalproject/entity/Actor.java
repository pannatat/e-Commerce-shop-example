package camt.cbsd.finalproject.entity;

import camt.cbsd.finalproject.config.json.View;
import camt.cbsd.finalproject.entity.security.Authority;
import camt.cbsd.finalproject.entity.security.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@JsonIgnoreType
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @JsonView(View.Login.class)
    String actorId;
    @JsonView(View.Login.class)
    String name;

    @OneToOne(mappedBy = "actor")
    @JsonManagedReference
    User user;

    @JsonView(View.Login.class)
    public List<Authority> getAuthorities(){
        return user.getAuthorities();
    }
}
