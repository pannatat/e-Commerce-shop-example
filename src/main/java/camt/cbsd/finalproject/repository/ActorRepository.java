package camt.cbsd.finalproject.repository;

import camt.cbsd.finalproject.entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor,Long>{
    Actor findById(Long id);
    Actor findByUserUsername(String username);
}
