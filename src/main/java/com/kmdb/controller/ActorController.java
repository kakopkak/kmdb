package com.kmdb.controller;

import com.kmdb.entity.Actor;
import com.kmdb.service.ActorService;
import dto.ActorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ResponseEntity<ActorResponse> getActors(@RequestParam(required = false) String name,
                                                   @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (name != null) {
            return new ResponseEntity<>(actorService.filterActorsByName(name, pageNo, pageSize), HttpStatus.OK);
        }
        return new ResponseEntity<>(actorService.findAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Long id) {
        return actorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(@Valid @RequestBody Actor actor) {
        return actorService.save(actor);
    }

    @PatchMapping("/{id}")
    public Actor updateActor(@PathVariable Long id, @Valid @RequestBody Actor actor) {
        actor.setId(id);
        return actorService.save(actor);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteActor(@PathVariable Long id, @RequestParam(required = false) boolean force) {
        int associatedMovies = actorService.findById(id).getMovies().size();
        if (force || associatedMovies == 0) {
            actorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                "Unable to delete actor '" + actorService.findById(id).getName() + "' as they are associated with " + associatedMovies + " movies.",
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllActors() {
        actorService.deleteAll();
    }
}
