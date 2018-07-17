package camt.cbsd.finalproject.service;

import camt.cbsd.finalproject.dao.ActorDao;
import camt.cbsd.finalproject.entity.Actor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@ConfigurationProperties(prefix = "server")
@Service
public class ActorServiceImpl implements ActorService{
    @Autowired
    ActorDao actorDao;


    @Override
    public List<Actor> getActors() {
        return actorDao.getActors();
    }

    @Override
    @Transactional
    public Actor findById(long id) {
        Actor actor=actorDao.findById(id);
        return actor;
    }

    @Override
    public Actor addActor(Actor actor) {
        return actorDao.addActor(actor);
    }

    @Override
    @Transactional
    public Actor getActorForTransfer(String username) {
        Actor actor=actorDao.findByUsername(username);
        Hibernate.initialize(actor.getAuthorities());
        return actor;
    }


}
