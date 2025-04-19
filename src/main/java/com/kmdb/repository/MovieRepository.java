package com.kmdb.repository;

import com.kmdb.entity.Actor;
import com.kmdb.entity.Genre;
import com.kmdb.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByGenres_Id(Long genreId, PageRequest pageable);
    Page<Movie> findByReleaseYear(Long releaseYear, PageRequest pageable);
    Page<Movie> findByActors_Id(Long actorId, PageRequest pageable);
    Page<Movie> findByTitleContainingIgnoreCase(String title, PageRequest pageable);
}

