package com.kmdb.service;

import com.kmdb.entity.Genre;
import com.kmdb.entity.Movie;
import com.kmdb.repository.MovieRepository;
import com.kmdb.exception.ResourceNotFoundException;
import dto.MovieResponse;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public MovieResponse findAll(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> movies = movieRepository.findAll(pageable);

        return getMovieResponse(movies);
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", id));
    }

    @Transactional
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    public Set<Genre> getGenresByMovieId(Long movieId) {
        Movie movie = findById(movieId);
        if (movie != null) {
            return movie.getGenres();
        }
        return null;
    }

    public MovieResponse filterByGenre(Long id, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> movies = movieRepository.findByGenres_Id(id, pageable);

        return getMovieResponse(movies);

    }

    public MovieResponse filterByActor(Long id, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> movies = movieRepository.findByActors_Id(id, pageable);

        return getMovieResponse(movies);
    }

    public MovieResponse filterByReleaseYear(Long releaseYear, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> movies = movieRepository.findByReleaseYear(releaseYear, pageable);

        return getMovieResponse(movies);
    }

    public MovieResponse filterByTitle(String title, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title, pageable);

        return getMovieResponse(movies);
    }

    @Transactional
    public void deleteAll() {
        movieRepository.deleteAll();
    }

    @NotNull
    private MovieResponse getMovieResponse(Page<Movie> movies) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setContent(movies.getContent());
        movieResponse.setPageNo(movies.getNumber());
        movieResponse.setPageSize(movies.getSize());
        movieResponse.setTotalElements(movies.getTotalElements());
        movieResponse.setTotalPages(movies.getTotalPages());
        movieResponse.setLast(movies.isLast());

        return movieResponse;
    }
}

