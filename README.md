# KMDB 

KMDB is a RESTful API service for managing a movie database, providing endpoints to create, read, update, and delete information about movies, actors, and genres.

## Overview

The KMDB application is built using Spring Boot with Java, offering a comprehensive set of APIs for managing a movie database. The application follows a standard layered architecture:

- **Controllers**: Handle HTTP requests and responses
- **Services**: Contain business logic
- **Repositories**: Handle data access
- **Entities**: Define the data model

## Key Features

- CRUD operations for movies, actors, and genres
- Many-to-many relationships between entities
- Pagination support for all list endpoints
- Filtering capabilities by various criteria
- Validation for all input data
- Proper error handling

## API Endpoints

### Movies

- `GET /api/movies`: Get all movies (paginated)
- `GET /api/movies/{id}`: Get a specific movie by ID
- `GET /api/movies/search?title={title}`: Search movies by title
- `GET /api/movies?genre={genreId}`: Filter movies by genre
- `GET /api/movies?year={year}`: Filter movies by release year
- `GET /api/movies?actor={actorId}`: Filter movies by actor
- `GET /api/movies/{id}/actors`: Get all actors in a movie
- `POST /api/movies`: Create a new movie
- `PATCH /api/movies/{id}`: Update a movie
- `DELETE /api/movies/{id}`: Delete a movie (with optional `force` parameter)
- `DELETE /api/movies`: Delete all movies

### Actors

- `GET /api/actors`: Get all actors (paginated)
- `GET /api/actors?name={name}`: Filter actors by name
- `GET /api/actors/{id}`: Get a specific actor by ID
- `POST /api/actors`: Create a new actor
- `PATCH /api/actors/{id}`: Update an actor
- `DELETE /api/actors/{id}`: Delete an actor (with optional `force` parameter)
- `DELETE /api/actors`: Delete all actors

### Genres

- `GET /api/genres`: Get all genres (paginated)
- `GET /api/genres/{id}`: Get a specific genre by ID
- `POST /api/genres`: Create a new genre
- `PATCH /api/genres/{id}`: Update a genre
- `DELETE /api/genres/{id}`: Delete a genre (with optional `force` parameter)
- `DELETE /api/genres`: Delete all genres

## Data Models

### Movie

- `id`: Long (auto-generated)
- `title`: String (required, 1-255 characters)
- `releaseYear`: int (min: 1888, max: 2100)
- `duration`: int (min: 1 minute)
- `genres`: Set of Genre objects
- `actors`: Set of Actor objects

### Actor

- `id`: Long (auto-generated)
- `name`: String (required, 1-255 characters)
- `birthDate`: LocalDate (required, must be in the past)
- `movies`: Set of Movie objects

### Genre

- `id`: Long (auto-generated)
- `name`: String (required)
- `movies`: Set of Movie objects

## Pagination

All list endpoints support pagination with the following query parameters:
- `pageNo`: Page number (zero-based, defaults to 0)
- `pageSize`: Number of items per page (defaults to 10)

## Error Handling

The API handles various error scenarios:
- Resource not found (404)
- Bad request for invalid input (400)
- Forbidden operations, like deleting entities with relationships (403)

## Technologies Used

- Spring Boot
- Spring Data JPA
- Jakarta Persistence
- Jakarta Validation
- Lombok

## Getting Started

1. Clone the repository
2. Configure your database settings in `application.properties`
3. Run the SQL script included in the repository to populate the database with initial data
4. Run the application using `./mvnw spring-boot:run` or through your IDE
5. Access the API at `http://localhost:8080/api`

## Postman Collection

A Postman collection is included in the repository (`KMDB API Collection.postman_collection.json`) to help test and interact with the API. The collection includes pre-configured requests for all available endpoints.

To use the collection:
1. Import the JSON file into Postman
2. Set the `base_url` variable to your server address (default: `http://localhost:8080`)
3. Use the organized request folders to test each endpoint

## Examples

### Creating a Movie

```json
POST /api/movies
{
  "title": "The Shawshank Redemption",
  "releaseYear": 1994,
  "duration": 142,
  "genres": [
    {"id": 1},
    {"id": 2}
  ],
  "actors": [
    {"id": 1},
    {"id": 2}
  ]
}
```

### Creating an Actor

```json
POST /api/actors
{
  "name": "Morgan Freeman",
  "birthDate": "1937-06-01"
}
```

### Creating a Genre

```json
POST /api/genres
{
  "name": "Drama"
}
```

## Database Initialization

To demonstrate the functionality, you can populate the database with sample data.

The repository contains SQL script file called "**sql-data-script.sql**" which can be run to populate the database. 