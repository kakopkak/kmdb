package com.kmdb.service;

import com.kmdb.entity.Genre;
import com.kmdb.entity.Movie;
import com.kmdb.exception.ResourceNotFoundException;
import com.kmdb.repository.GenreRepository;
import dto.GenreResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public GenreResponse findAll(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Genre> genres = genreRepository.findAll(pageable);

        GenreResponse genreResponse = new GenreResponse();
        genreResponse.setContent(genres.getContent());
        genreResponse.setPageNo(genres.getNumber());
        genreResponse.setPageSize(genres.getSize());
        genreResponse.setTotalElements(genres.getTotalElements());
        genreResponse.setTotalPages(genres.getTotalPages());
        genreResponse.setLast(genres.isLast());

        return genreResponse;
    }

    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre", id));
    }

    @Transactional
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

    @Transactional
    public Genre addMovieToGenre(Long genreId, Movie movie) {
        Genre genre = findById(genreId);
        if (genre != null) {
            genre.addMovie(movie);
            return save(genre);
        }
        return null;
    }

    @Transactional
    public Genre removeMovieFromGenre(Long genreId, Long movieId) {
        Genre genre = findById(genreId);
        if (genre != null) {
            Movie movie = genre.getMovies().stream().filter(c -> c.getId().equals(movieId)).findFirst().orElse(null);
            if (movie != null) {
                genre.removeMovie(movie);
                return save(genre);
            }
        }
        return null;
    }

    @Transactional
    public void deleteAll() {
        genreRepository.deleteAll();
    }
}
