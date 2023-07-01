package com.oguzhantuncer.todo.service;

import com.oguzhantuncer.todo.exception.BusinessException;
import com.oguzhantuncer.todo.exception.DomainNotFoundException;
import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.enums.TodoStatus;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo save(TodoRequest request) {
        log.info("TodoSevice.save() triggered for this request: {}", request);
        Optional<Todo> todoFromRepository = todoRepository.findByName(request.getName());
        if (todoFromRepository.isPresent()) {
            log.error("Kayit edilmek istenilen todo zaten mevcuttur");
            throw new BusinessException("todo.is.already.exists");
        }
        Todo todo = Todo.toEntity(request);
        log.info("TodoService.save() is completed. Persisted todo is {}",todo);
        return todoRepository.save(todo);
    }

    public Todo update(TodoRequest request, Long id) {
        log.info("TodoService.update() triggered for this id: {} and request: {}", id, request);
        Optional<Todo> byId = todoRepository.findById(id);
        if (byId.isEmpty()) {
            log.error("TODO veritabaninda bulunamadi");
            throw new DomainNotFoundException("todo.is.not.found");
        }
        Todo todo = byId.get();
        todo.setName(request.getName());
        todo.setStatus(request.getStatus());
        log.info("TodoService.update() is completed. Updated todo is {}", request);
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        log.info("TodoService.findAll() triggered");
        return todoRepository.findAll();
    }

    public void delete(Long id) {
        log.info("TodoService.delete() triggered for this id: {}", id);
        todoRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        log.info("TodoService.deleteAll() triggered");
        todoRepository.deleteAll();
    }

    @Transactional
    public void deleteAllCompleted() {
        log.info("TodoService.deleteAllCompleted() triggered");
        todoRepository.deleteByStatus(TodoStatus.COMPLETED);
    }

    public List<Todo> findAllCompleted() {
        log.info("TodoService.findAllCompleted() triggered");
        return todoRepository.findAllByStatus(TodoStatus.COMPLETED);
    }

    public List<Todo> findAllNotCompleted() {
        log.info("TodoService.findAllNotCompleted() triggered");
        return todoRepository.findAllByStatus(TodoStatus.NOT_COMPLETED);
    }
}

