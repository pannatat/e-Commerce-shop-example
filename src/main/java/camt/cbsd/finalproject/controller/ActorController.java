package camt.cbsd.finalproject.controller;

import camt.cbsd.finalproject.entity.Actor;
import camt.cbsd.finalproject.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class ActorController {

    ActorService actorService;

    @Autowired
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    @CrossOrigin
    @GetMapping("/actor")
    public ResponseEntity<?> getActors(){
        List<Actor> actors = actorService.getActors();
        return ResponseEntity.ok(actors);
    }

}
