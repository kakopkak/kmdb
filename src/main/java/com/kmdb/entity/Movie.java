package com.kmdb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Max(value = 2100, message = "Release year cannot be in the distant future")
    @Min(value = 1888, message = "Release year must be at least 1888 (first film ever made)")
    private int releaseYear;
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int duration;


    @ManyToMany(mappedBy = "movies")
    private Set<Genre> genres = new HashSet<>();


    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors = new HashSet<>();

    public Movie() {
    }

    public Movie(String title, int releaseYear, int duration) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public int getDuration() {
        return this.duration;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getMovies().add(this);
    }

    public void addActor(Actor actor) {
        this.actors.add(actor);
        actor.getMovies().add(this);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getMovies().remove(this);
    }

    public void removeActor(Actor actor) {
        this.actors.remove(actor);
        actor.getMovies().remove(this);
    }

    public void removeAllGenres() {
        for (Genre genre : this.genres) {
            genre.getMovies().remove(this);
        }
        this.genres.clear();
    }

    public void removeAllActors() {
        for (Actor actor : this.actors) {
            actor.getMovies().remove(this);
        }
        this.actors.clear();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Movie))
            return false;
        Movie movie = (Movie) o;
        return Objects.equals(this.id, movie.id) && Objects.equals(this.releaseYear, movie.releaseYear)
                && Objects.equals(this.title, movie.title) && Objects.equals(this.duration, movie.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.releaseYear, this.duration);
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + this.id + ", title='" + this.title + '\'' + ", releaseYear='" + this.releaseYear + '\'' + ", duration='" + this.duration + '\'' + '}';
    }
}

