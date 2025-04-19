package com.kmdb.controller;

import com.kmdb.entity.Genre;
import com.kmdb.service.GenreService;
import dto.GenreResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<GenreResponse> getAllGenres(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(genreService.findAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreService.save(genre);
    }


    @PatchMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @Valid @RequestBody Genre genre) {
        genre.setId(id);
        return genreService.save(genre);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteGenre(@PathVariable Long id, @RequestParam(required = false) boolean force) {
        int associatedMovies = genreService.findById(id).getMovies().size();
        if (force || associatedMovies == 0) {
            genreService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                "Cannot delete genre '" + genreService.findById(id).getName() + "' because it has " + associatedMovies + " associated movies.",
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllGenres() {
        genreService.deleteAll();
    }
}
