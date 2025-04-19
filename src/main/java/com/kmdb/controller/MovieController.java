package com.kmdb.controller;

import com.kmdb.entity.Movie;
import com.kmdb.entity.Genre;
import com.kmdb.entity.Actor;
import com.kmdb.exception.BadRequestException;
import com.kmdb.exception.ForbiddenOperationException;
import com.kmdb.exception.ResourceNotFoundException;
import com.kmdb.service.MovieService;
import com.kmdb.service.GenreService;
import com.kmdb.service.ActorService;
import dto.MovieResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ActorService actorService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<MovieResponse> getMovies(@RequestParam(required = false) Long genre,
                                   @RequestParam(required = false) Long year,
                                   @RequestParam(required = false) Long actor,
                                   @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (genre != null) {
            return new ResponseEntity<>(movieService.filterByGenre(genre, pageNo, pageSize), HttpStatus.OK);
        }
        if (year != null) {
            return new ResponseEntity<>(movieService.filterByReleaseYear(year, pageNo, pageSize), HttpStatus.OK);
        }
        if (actor != null) {
            return new ResponseEntity<>(movieService.filterByActor(actor, pageNo, pageSize), HttpStatus.OK);
        }
        return new ResponseEntity<>(movieService.findAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    Movie one(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<MovieResponse> getMoviesByTitle(@RequestParam() String title,
                                                          @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(movieService.filterByTitle(title, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}/actors")
    Set<Actor> allActors(@PathVariable Long id) {
        return movieService.findById(id).getActors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Movie newMovie(@Valid @RequestBody Movie newMovie) {
        Movie movie = new Movie(newMovie.getTitle(), newMovie.getReleaseYear(), newMovie.getDuration());

        movie = movieService.save(movie);

        if (newMovie.getGenres() != null && !newMovie.getGenres().isEmpty()) {
            Set<Genre> genres = new HashSet<>();
            for (Genre requestGenre : newMovie.getGenres()) {
                try {
                    Genre genre = genreService.findById(requestGenre.getId());
                    genre.addMovie(movie);
                    genreService.save(genre);
                    genres.add(genre);
                } catch (ResourceNotFoundException e) {
                    throw new BadRequestException("Genre with id " + requestGenre.getId() + " not found");
                }
            }
            movie.setGenres(genres);
        }

        if (newMovie.getActors() != null && !newMovie.getActors().isEmpty()) {
            Set<Actor> actors = new HashSet<>();
            for (Actor requestActor : newMovie.getActors()) {
                try {
                    Actor actor = actorService.findById(requestActor.getId());
                    actor.addMovie(movie);
                    actorService.save(actor);
                    actors.add(actor);
                } catch (ResourceNotFoundException e) {
                    throw new BadRequestException("Actor with id " + requestActor.getId() + " not found");
                }
            }
            movie.setActors(actors);
        }
        return movieService.save(movie);
    }

    @PatchMapping("/{id}")
    Movie patchMovie(@PathVariable Long id, @Valid @RequestBody Movie updatedMovie) {
        Movie movie = movieService.findById(id);

        if (updatedMovie.getTitle() != null) {
            movie.setTitle(updatedMovie.getTitle());
        }

        if (updatedMovie.getReleaseYear() != 0) {
            movie.setReleaseYear(updatedMovie.getReleaseYear());
        }

        if (updatedMovie.getDuration() != 0) {
            movie.setDuration(updatedMovie.getDuration());
        }

        if (updatedMovie.getGenres() != null) {
            movie.removeAllGenres();

            for (Genre requestGenre : updatedMovie.getGenres()) {
                try {
                    Genre genre = genreService.findById(requestGenre.getId());
                    genre.addMovie(movie);
                    genreService.save(genre);
                } catch (ResourceNotFoundException e) {
                    throw new BadRequestException("Genre with id " + requestGenre.getId() + " not found");
                }
            }
        }

        if (updatedMovie.getActors() != null) {
            movie.removeAllActors();

            for (Actor requestActor : updatedMovie.getActors()) {
                try {
                    Actor actor = actorService.findById(requestActor.getId());
                    actor.addMovie(movie);
                    actorService.save(actor);
                } catch (ResourceNotFoundException e) {
                    throw new BadRequestException("Actor with id " + requestActor.getId() + " not found");
                }
            }
        }
        return movieService.save(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id, @RequestParam(required = false) boolean force) {
        Movie movie = movieService.findById(id);
        int associatedActors = movie.getActors().size();
        int associatedGenres = movie.getGenres().size();

        if (!force && (associatedActors > 0 || associatedGenres > 0)) {
            throw new ForbiddenOperationException(
                    "Unable to delete movie '" + movie.getTitle() +
                            "' because it has " + associatedActors + " actors and " +
                            associatedGenres + " genres. Use force=true to delete anyway.");
        }

        movieService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAllMovies() {
        movieService.deleteAll();
    }
}