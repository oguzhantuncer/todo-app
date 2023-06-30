package com.oguzhantuncer.todo.repository;

import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.enums.TodoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    void deleteByStatus(TodoStatus status);
    List<Todo> findAllByStatus(TodoStatus status);
    Optional<Todo> findByName(String name);
}
