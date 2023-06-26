package com.oguzhantuncer.todo.service;

import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.enums.TodoStatus;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo save(TodoRequest request) {
        Todo todo = new Todo();
        todo.setContent(request.getContent());
        todo.setStatus(TodoStatus.NOT_COMPLETED);
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }


    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo update(TodoRequest request,Long id) {
        Todo todo = todoRepository.findById(id).get();
        todo.setContent(request.getContent());
        todo.setStatus(request.getStatus());
        return todoRepository.save(todo);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }

    public void deleteAllDone() {
    }
}
