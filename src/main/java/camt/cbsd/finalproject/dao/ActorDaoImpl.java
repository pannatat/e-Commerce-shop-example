package camt.cbsd.finalproject.dao;

import camt.cbsd.finalproject.entity.Actor;
import camt.cbsd.finalproject.repository.ActorRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Profile("DBDataSource")
public class ActorDaoImpl implements ActorDao {
    ActorRepository actorRepository;

    @Autowired
    public void setActorRepository(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> getActors() {
        return Lists.newArrayList(actorRepository.findAll());
    }

    @Override
    public Actor findById(long id) {
        return actorRepository.findById(id);
    }

    @Override
    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor findByUsername(String username) {
        return actorRepository.findByUserUsername(username);
    }
}
