package com.oguzhantuncer.todo.service;

import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void save(TodoRequest request) {
        Todo todo = new Todo();
        todo.setContent(request.getContent());
        todo.setStatus(request.getStatus());
        todoRepository.save(todo);
    }
}
