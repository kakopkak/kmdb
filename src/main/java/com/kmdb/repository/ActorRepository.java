package com.kmdb.repository;

import com.kmdb.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Page<Actor> findActorByNameContainingIgnoreCase(String name, PageRequest pageable);
}
