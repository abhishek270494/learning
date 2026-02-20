package com.example.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learning.model.Todo;

@Repository
public interface ITodoRepository extends JpaRepository<Todo, Long> {

}
