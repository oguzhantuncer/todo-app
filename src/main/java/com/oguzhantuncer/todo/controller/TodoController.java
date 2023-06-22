package com.oguzhantuncer.todo.controller;

import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public void save(@RequestBody TodoRequest request){
        todoService.save(request);
    }
}
