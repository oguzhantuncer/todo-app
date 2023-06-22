package com.oguzhantuncer.todo.model.entity;

import com.oguzhantuncer.todo.model.enums.TodoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TodoStatus status;
    @Column(name = "content")
    private String content;
}
