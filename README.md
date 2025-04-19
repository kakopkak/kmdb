<div align="center">


<h3 align="center">KMDB</h3>

  <p align="center">
    A REST API for managing movies, actors, and genres using Spring Boot and SQLite.
    <br />
     <a href="https://github.com/kakopkak/kmdb">https://github.com/kakopkak/kmdb</a>
  </p>
</div>

## Table of Contents

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#key-features">Key Features</a></li>
      </ul>
    </li>
    <li><a href="#architecture">Architecture</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

## About The Project

KMDB is a Spring Boot-based REST API that provides functionalities to manage movies, actors, and genres. It uses SQLite as the database. The application allows users to perform CRUD operations on movies, actors, and genres, as well as filter movies based on genre, release year, and actor. It also includes exception handling and validation for data integrity.

### Key Features

- **CRUD Operations:** Create, Read, Update, and Delete movies, actors, and genres.
- **Filtering:** Filter movies by genre, release year, or actor.
- **Pagination:** Implemented for retrieving large datasets of movies, actors, and genres.
- **Validation:** Data validation using Jakarta Validation annotations to ensure data integrity.
- **Exception Handling:** Global exception handling for common errors like resource not found, bad requests, and forbidden operations.
- **SQLite Database:** Uses SQLite for data persistence.

## Architecture

![Architecture Diagram](https://github.com/user-attachments/assets/721b7fb3-e480-4809-9023-fd48b82b1f8c)

The application follows a layered architecture:

- **Controller Layer:** Exposes REST endpoints for interacting with the application. Uses `MovieController`, `ActorController`, and `GenreController`.
- **Service Layer:** Contains the business logic for managing movies, actors, and genres. Uses `MovieService`, `ActorService`, and `GenreService`.
- **Repository Layer:** Provides data access using Spring Data JPA repositories. Uses `MovieRepository`, `ActorRepository`, and `GenreRepository`.
- **Entity Layer:** Defines the data model for movies, actors, and genres. Uses `Movie`, `Actor`, and `Genre` entities.
- **Database:** SQLite database for persistent storage.

The application uses the following technologies:

- **Spring Boot:** Framework for building the application.
- **Spring Data JPA:** Simplifies database access.
- **SQLite:** Embedded database for storing data.
- **Jakarta Validation:** For validating data input.
- **Maven:** Build automation tool.

## Getting Started

### Prerequisites

- Java 21
- Maven
- An IDE such as IntelliJ IDEA or Eclipse

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/kakopkak/kmdb.git
   cd kmdb
   ```
2. Build the project using Maven:
   ```sh
   ./mvnw clean install
   ```
3. Run the application:
   ```sh
   ./mvnw spring-boot:run
   ```
   Alternatively, you can run the `KmdbApplication.java` file directly from your IDE.

4. The API will be accessible at `http://localhost:8080/api`.
