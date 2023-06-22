package com.oguzhantuncer.todo.model.request;

import com.oguzhantuncer.todo.model.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest {
    private String content;
    private TodoStatus status;
}
