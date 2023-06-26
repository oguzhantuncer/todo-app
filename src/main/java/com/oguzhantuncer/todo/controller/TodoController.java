package com.oguzhantuncer.todo.controller;

import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/todo")
@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {

        this.todoService = todoService;
    }

    @PostMapping
    public Todo save(@RequestBody TodoRequest request){
        return todoService.save(request);
    }

    @GetMapping
    public List<Todo> getAll(){
        return todoService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    @DeleteMapping
    public void deleteAll(){
        todoService.deleteAll();
    }

    @DeleteMapping
    public void deleteAllDone(){
        todoService.deleteAllDone();
    }

    @PutMapping("/{id}")
    private Todo update(@RequestBody TodoRequest request, @PathVariable Long id){
        return todoService.update(request,id);
    }
}
