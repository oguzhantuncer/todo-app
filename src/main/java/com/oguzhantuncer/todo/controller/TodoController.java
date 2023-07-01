package com.oguzhantuncer.todo.controller;

import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2

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

    @PutMapping("/{id}")
    private Todo update(@PathVariable Long id, @RequestBody TodoRequest request){
        return todoService.update(id,request);
    }

    @GetMapping
    public List<Todo> getAll(){
        return todoService.findAll();
    }

    @GetMapping("/completed")
    public List<Todo> getAllCompleted(){
        return todoService.findAllCompleted();
    }

    @GetMapping("/not-completed")
    public List<Todo> getAllNotCompleted(){
        return todoService.findAllNotCompleted();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        todoService.delete(id);
    }

    @DeleteMapping
    public void deleteAll(){
        todoService.deleteAll();
    }

    @DeleteMapping("/completed")
    public void deleteAllDone(){
        todoService.deleteAllCompleted();
    }
}
