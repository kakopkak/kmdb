-- Drop tables if they already exist (for repeatable runs)
DROP TABLE IF EXISTS actor_movie;
DROP TABLE IF EXISTS genre_movie;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS genre;

-- Create Genres table
CREATE TABLE genre (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       name VARCHAR(255) NOT NULL
);

-- Create Actors table
CREATE TABLE actor (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       name VARCHAR(255) NOT NULL,
                       birth_date TEXT NOT NULL -- Store as 'YYYY-MM-DD'
);

-- Create Movies table
CREATE TABLE movie (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       title VARCHAR(255) NOT NULL,
                       release_year INTEGER NOT NULL,
                       duration INTEGER NOT NULL
);

-- Create Genre-Movie relationship table
CREATE TABLE genre_movie (
                             genre_id INTEGER,
                             movie_id INTEGER,
                             PRIMARY KEY (genre_id, movie_id),
                             FOREIGN KEY (genre_id) REFERENCES genre(id),
                             FOREIGN KEY (movie_id) REFERENCES movie(id)
);

-- Create Actor-Movie relationship table
CREATE TABLE actor_movie (
                             actor_id INTEGER,
                             movie_id INTEGER,
                             PRIMARY KEY (actor_id, movie_id),
                             FOREIGN KEY (actor_id) REFERENCES actor(id),
                             FOREIGN KEY (movie_id) REFERENCES movie(id)
);

-- Insert Genres (12)
INSERT INTO genre (name) VALUES ('Action');
INSERT INTO genre (name) VALUES ('Science Fiction');
INSERT INTO genre (name) VALUES ('Adventure');
INSERT INTO genre (name) VALUES ('Drama');
INSERT INTO genre (name) VALUES ('Comedy');
INSERT INTO genre (name) VALUES ('Horror');
INSERT INTO genre (name) VALUES ('Thriller');
INSERT INTO genre (name) VALUES ('Romance');
INSERT INTO genre (name) VALUES ('Fantasy');
INSERT INTO genre (name) VALUES ('Animation');
INSERT INTO genre (name) VALUES ('Crime');
INSERT INTO genre (name) VALUES ('Mystery');

-- Insert Actors (15)
INSERT INTO actor (name, birth_date) VALUES ('Keanu Reeves', '1964-09-02');
INSERT INTO actor (name, birth_date) VALUES ('Carrie-Anne Moss', '1967-08-21');
INSERT INTO actor (name, birth_date) VALUES ('Leonardo DiCaprio', '1974-11-11');
INSERT INTO actor (name, birth_date) VALUES ('Brad Pitt', '1963-12-18');
INSERT INTO actor (name, birth_date) VALUES ('Tom Hanks', '1956-07-09');
INSERT INTO actor (name, birth_date) VALUES ('Meryl Streep', '1949-06-22');
INSERT INTO actor (name, birth_date) VALUES ('Scarlett Johansson', '1984-11-22');
INSERT INTO actor (name, birth_date) VALUES ('Robert Downey Jr.', '1965-04-04');
INSERT INTO actor (name, birth_date) VALUES ('Jennifer Lawrence', '1990-08-15');
INSERT INTO actor (name, birth_date) VALUES ('Denzel Washington', '1954-12-28');
INSERT INTO actor (name, birth_date) VALUES ('Tom Cruise', '1962-07-03');
INSERT INTO actor (name, birth_date) VALUES ('Angelina Jolie', '1975-06-04');
INSERT INTO actor (name, birth_date) VALUES ('Morgan Freeman', '1937-06-01');
INSERT INTO actor (name, birth_date) VALUES ('Natalie Portman', '1981-06-09');
INSERT INTO actor (name, birth_date) VALUES ('Christian Bale', '1974-01-30');

-- Insert Movies (20, years 1980–2018)
INSERT INTO movie (title, release_year, duration) VALUES ('The Matrix', 1999, 136);
INSERT INTO movie (title, release_year, duration) VALUES ('Inception', 2010, 148);
INSERT INTO movie (title, release_year, duration) VALUES ('Forrest Gump', 1994, 142);
INSERT INTO movie (title, release_year, duration) VALUES ('The Shawshank Redemption', 1994, 142);
INSERT INTO movie (title, release_year, duration) VALUES ('Pulp Fiction', 1994, 154);
INSERT INTO movie (title, release_year, duration) VALUES ('The Dark Knight', 2008, 152);
INSERT INTO movie (title, release_year, duration) VALUES ('Fight Club', 1999, 139);
INSERT INTO movie (title, release_year, duration) VALUES ('Interstellar', 2014, 169);
INSERT INTO movie (title, release_year, duration) VALUES ('The Godfather', 1972, 175);
INSERT INTO movie (title, release_year, duration) VALUES ('The Lord of the Rings: The Fellowship of the Ring', 2001, 178);
INSERT INTO movie (title, release_year, duration) VALUES ('Jurassic Park', 1993, 127);
INSERT INTO movie (title, release_year, duration) VALUES ('Titanic', 1997, 195);
INSERT INTO movie (title, release_year, duration) VALUES ('The Avengers', 2012, 143);
INSERT INTO movie (title, release_year, duration) VALUES ('The Shining', 1980, 146);
INSERT INTO movie (title, release_year, duration) VALUES ('La La Land', 2016, 128);
INSERT INTO movie (title, release_year, duration) VALUES ('Gladiator', 2000, 155);
INSERT INTO movie (title, release_year, duration) VALUES ('The Social Network', 2010, 120);
INSERT INTO movie (title, release_year, duration) VALUES ('The Departed', 2006, 151);
INSERT INTO movie (title, release_year, duration) VALUES ('Mad Max: Fury Road', 2015, 120);
INSERT INTO movie (title, release_year, duration) VALUES ('Black Panther', 2018, 134);

-- Link Genres to Movies (at least 2 per genre, some movies have multiple genres)
INSERT INTO genre_movie VALUES (1, 1); -- Action: The Matrix
INSERT INTO genre_movie VALUES (2, 1); -- Sci-Fi: The Matrix
INSERT INTO genre_movie VALUES (2, 2); -- Sci-Fi: Inception
INSERT INTO genre_movie VALUES (1, 2); -- Action: Inception
INSERT INTO genre_movie VALUES (7, 2); -- Thriller: Inception
INSERT INTO genre_movie VALUES (4, 3); -- Drama: Forrest Gump
INSERT INTO genre_movie VALUES (5, 3); -- Comedy: Forrest Gump
INSERT INTO genre_movie VALUES (4, 4); -- Drama: Shawshank Redemption
INSERT INTO genre_movie VALUES (4, 5); -- Drama: Pulp Fiction
INSERT INTO genre_movie VALUES (11, 5); -- Crime: Pulp Fiction
INSERT INTO genre_movie VALUES (1, 6); -- Action: The Dark Knight
INSERT INTO genre_movie VALUES (4, 6); -- Drama: The Dark Knight
INSERT INTO genre_movie VALUES (11, 6); -- Crime: The Dark Knight
INSERT INTO genre_movie VALUES (4, 7); -- Drama: Fight Club
INSERT INTO genre_movie VALUES (4, 8); -- Drama: Interstellar
INSERT INTO genre_movie VALUES (2, 8); -- Sci-Fi: Interstellar
INSERT INTO genre_movie VALUES (3, 8); -- Adventure: Interstellar
INSERT INTO genre_movie VALUES (4, 9); -- Drama: The Godfather
INSERT INTO genre_movie VALUES (11, 9); -- Crime: The Godfather
INSERT INTO genre_movie VALUES (9, 10); -- Fantasy: LOTR
INSERT INTO genre_movie VALUES (3, 10); -- Adventure: LOTR
INSERT INTO genre_movie VALUES (3, 11); -- Adventure: Jurassic Park
INSERT INTO genre_movie VALUES (2, 11); -- Sci-Fi: Jurassic Park
INSERT INTO genre_movie VALUES (8, 12); -- Romance: Titanic
INSERT INTO genre_movie VALUES (4, 12); -- Drama: Titanic
INSERT INTO genre_movie VALUES (1, 13); -- Action: Avengers
INSERT INTO genre_movie VALUES (9, 13); -- Fantasy: Avengers
INSERT INTO genre_movie VALUES (7, 14); -- Thriller: The Shining
INSERT INTO genre_movie VALUES (6, 14); -- Horror: The Shining
INSERT INTO genre_movie VALUES (8, 15); -- Romance: La La Land
INSERT INTO genre_movie VALUES (5, 15); -- Comedy: La La Land
INSERT INTO genre_movie VALUES (1, 16); -- Action: Gladiator
INSERT INTO genre_movie VALUES (4, 16); -- Drama: Gladiator
INSERT INTO genre_movie VALUES (4, 17); -- Drama: The Social Network
INSERT INTO genre_movie VALUES (4, 18); -- Drama: The Departed
INSERT INTO genre_movie VALUES (11, 18); -- Crime: The Departed
INSERT INTO genre_movie VALUES (1, 19); -- Action: Mad Max
INSERT INTO genre_movie VALUES (3, 19); -- Adventure: Mad Max
INSERT INTO genre_movie VALUES (9, 20); -- Fantasy: Black Panther
INSERT INTO genre_movie VALUES (1, 20); -- Action: Black Panther

-- Link Actors to Movies (each actor in at least 1, some in multiple)
INSERT INTO actor_movie VALUES (1, 1); -- Keanu Reeves: The Matrix
INSERT INTO actor_movie VALUES (2, 1); -- Carrie-Anne Moss: The Matrix
INSERT INTO actor_movie VALUES (3, 2); -- Leonardo DiCaprio: Inception
INSERT INTO actor_movie VALUES (4, 5); -- Brad Pitt: Pulp Fiction
INSERT INTO actor_movie VALUES (4, 7); -- Brad Pitt: Fight Club
INSERT INTO actor_movie VALUES (5, 3); -- Tom Hanks: Forrest Gump
INSERT INTO actor_movie VALUES (6, 12); -- Meryl Streep: Titanic
INSERT INTO actor_movie VALUES (7, 13); -- Scarlett Johansson: Avengers
INSERT INTO actor_movie VALUES (8, 13); -- Robert Downey Jr.: Avengers
INSERT INTO actor_movie VALUES (9, 8); -- Jennifer Lawrence: Interstellar
INSERT INTO actor_movie VALUES (10, 18); -- Denzel Washington: The Departed
INSERT INTO actor_movie VALUES (11, 11); -- Tom Cruise: Jurassic Park
INSERT INTO actor_movie VALUES (12, 19); -- Angelina Jolie: Mad Max
INSERT INTO actor_movie VALUES (13, 4); -- Morgan Freeman: Shawshank
INSERT INTO actor_movie VALUES (14, 15); -- Natalie Portman: La La Land
INSERT INTO actor_movie VALUES (15, 6); -- Christian Bale: The Dark Knight
INSERT INTO actor_movie VALUES (3, 12); -- Leonardo DiCaprio: Titanic
INSERT INTO actor_movie VALUES (5, 17); -- Tom Hanks: The Social Network
INSERT INTO actor_movie VALUES (13, 6); -- Morgan Freeman: The Dark Knight
INSERT INTO actor_movie VALUES (14, 20); -- Natalie Portman: Black Panther
