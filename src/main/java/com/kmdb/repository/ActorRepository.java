package com.kmdb.repository;

import com.kmdb.entity.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Page<Actor> findActorByName(String name, PageRequest pageable);
}
