package com.oguzhantuncer.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.enums.TodoStatus;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TodoController.class)
class TodoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void it_should_post_todo() throws Exception {
        //given
        TodoRequest request = new TodoRequest();
        request.setName("todo-name");
        request.setStatus(TodoStatus.NOT_COMPLETED);

        //when
        ResultActions result = mockMvc.perform(post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        result.andExpect(status().isOk());

        verify(todoService).save(request);
    }

    @Test
    void it_should_get_all_todos() throws Exception {
        //given
        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoService.findAll()).thenReturn(List.of(response));

        //when
        ResultActions result = mockMvc.perform(get("/todo")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk());

        verify(todoService).findAll();
    }

    @Test
    void it_should_get_all_completed_todos() throws Exception {
        //given
        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.COMPLETED);

        when(todoService.findAll()).thenReturn(List.of(response));

        //when
        ResultActions result = mockMvc.perform(get("/todo/completed")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk());

        verify(todoService).findAllCompleted();
    }

    @Test
    void it_should_get_all_not_completed_todos() throws Exception {
        //given
        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoService.findAll()).thenReturn(List.of(response));

        //when
        ResultActions result = mockMvc.perform(get("/todo/not-completed")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk());

        verify(todoService).findAllNotCompleted();
    }

    @Test
    void it_should_delete_todo() throws Exception {
        //given
        Long id=123L;

        //when
        ResultActions result = mockMvc.perform(delete("/todo/123")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk());

        verify(todoService).delete(id);
    }

    @Test
    void it_should_delete_todo_all_todos() throws Exception {
        //given

        //when
        ResultActions result = mockMvc.perform(delete("/todo")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk());

        verify(todoService).deleteAll();
    }

    @Test
    void it_should_delete_todo_all_completed_todos() throws Exception {
        //given

        //when
        ResultActions result = mockMvc.perform(delete("/todo/completed")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk());

        verify(todoService).deleteAllCompleted();
    }

    @Test
    void it_should_update_todo() throws Exception {
        //given
        Long id=123L;

        TodoRequest request = new TodoRequest();
        request.setName("todo-name");
        request.setStatus(TodoStatus.NOT_COMPLETED);

        //when
        ResultActions result = mockMvc.perform(put("/todo/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        result.andExpect(status().isOk());

        verify(todoService).update(id, request);
    }
}
