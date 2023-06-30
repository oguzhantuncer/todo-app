package com.oguzhantuncer.todo.model.entity;

import com.oguzhantuncer.todo.model.enums.TodoStatus;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TodoStatus status;

    @Column(name = "content")
    private String name;

    public static Todo toEntity(TodoRequest request) {
        Todo todo = new Todo();
        todo.setName(request.getName());
        todo.setStatus(TodoStatus.NOT_COMPLETED);
        return todo;
    }
}
