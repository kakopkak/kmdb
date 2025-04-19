package com.kmdb.service;

import com.kmdb.entity.Actor;
import com.kmdb.entity.Movie;
import com.kmdb.exception.ResourceNotFoundException;
import com.kmdb.repository.ActorRepository;
import dto.ActorResponse;
import dto.GenreResponse;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    public ActorResponse findAll(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Actor> actors = actorRepository.findAll(pageable);

        return getActorResponse(actors);
    }

    public Actor findById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor", id));
    }

    @Transactional
    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }

    @Transactional
    public Actor addMovieToActor(Long actorId, Movie movie) {
        Actor actor = findById(actorId);
        if (actor != null) {
            actor.addMovie(movie);
            return save(actor);
        }
        return null;
    }

    @Transactional
    public Actor removeMovieFromActor(Long actorId, Long movieId) {
        Actor actor = findById(actorId);
        if (actor != null) {
            Movie movie = actor.getMovies().stream().filter(c -> c.getId().equals(movieId)).findFirst().orElse(null);
            if (movie != null) {
                actor.removeMovie(movie);
                return save(actor);
            }
        }
        return null;
    }

    public ActorResponse filterActorsByName(String name, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Actor> actors = actorRepository.findActorByName(name, pageable);

        return getActorResponse(actors);
    }

    @NotNull
    private ActorResponse getActorResponse(Page<Actor> actors) {
        ActorResponse actorResponse = new ActorResponse();
        actorResponse.setContent(actors.getContent());
        actorResponse.setPageNo(actors.getNumber());
        actorResponse.setPageSize(actors.getSize());
        actorResponse.setTotalElements(actors.getTotalElements());
        actorResponse.setTotalPages(actors.getTotalPages());
        actorResponse.setLast(actors.isLast());

        return actorResponse;
    }

    @Transactional
    public void deleteAll() {
        actorRepository.deleteAll();
    }
}
