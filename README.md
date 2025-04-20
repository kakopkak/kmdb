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
    <li>
      <a href="#testing-the-api">Testing the API</a>
      <ul>
        <li><a href="#using-postman">Using Postman</a></li>
        <li><a href="#example-requests">Example Requests</a></li>
        <li><a href="#error-handling-and-validation">Error Handling and Validation</a></li>
      </ul>
    </li>
    <li><a href="#future-enhancements">Future Enhancements</a></li>
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

## Testing the API

### Using Postman
A **Postman** collection named **Movie Database API** is available in the repository. Import it into Postman to test the API endpoints easily.

### Example Requests

#### 1. Create a New Genre
```
POST /api/genres
Content-Type: application/json

{
  "name": "Adventure"
}
```

#### 2. Get Movies by Genre
```
GET /api/movies?genre=1
```

#### 3. Search Movies by Title
```
GET /api/movies/search?title=matrix
```

#### 4. Update an Actor's Name
```
PATCH /api/actors/1
Content-Type: application/json

{
  "name": "Leonardo DiCaprio Jr."
}
```

#### 5. Delete a Genre with Cascade
```
DELETE /api/genres/1?cascade=true
```

### Error Handling and Validation
* **404 Not Found**: Returned when a requested resource does not exist.
* **400 Bad Request**: Returned when validation fails or input data is invalid.
* **Validation Annotations**:
   * `@NotBlank`: Ensures that a string is not null or empty.
   * `@Past`: Validates that a date is in the past.
   * `@Min` and `@Max`: Checks numerical constraints.
* **Exception Handling**:
   * A `@ControllerAdvice` class handles exceptions globally and returns meaningful error responses.

#### Example: Handling Invalid Birth Date
```
POST /api/actors
Content-Type: application/json

{
  "name": "John Doe",
  "birthDate": "2025-01-01"
}
```

**Response**:
```
HTTP/1.1 400 Bad Request
Content-Type: application/json

{
  "timestamp": "2024-10-01T12:00:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Birth date must be in the past",
  "path": "/api/actors"
}
```

## Future Enhancements
* **Authentication and Authorization**: Implement security measures using Spring Security.
* **Swagger Documentation**: Integrate Swagger for API documentation.
* **Bulk Operations**: Add endpoints for bulk creation and updates.
* **Advanced Search**: Implement more complex search queries and filters.
* **Internationalization**: Support multiple languages for data input and output.

## Acknowledgments
