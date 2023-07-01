package com.oguzhantuncer.todo.service;

import com.oguzhantuncer.todo.exception.BusinessException;
import com.oguzhantuncer.todo.exception.DomainNotFoundException;
import com.oguzhantuncer.todo.model.entity.Todo;
import com.oguzhantuncer.todo.model.enums.TodoStatus;
import com.oguzhantuncer.todo.model.request.TodoRequest;
import com.oguzhantuncer.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    void it_should_save_todo() {
        //given
        TodoRequest request = new TodoRequest();
        request.setName("todo-name");
        request.setStatus(TodoStatus.NOT_COMPLETED);

        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoRepository.findByName(request.getName())).thenReturn(Optional.empty());
        when(todoRepository.save(Todo.toEntity(request))).thenReturn(response);

        //when
        Todo savedTodo = todoService.save(request);

        //then
        ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository).save(todoArgumentCaptor.capture());
        Todo capturedTodo = todoArgumentCaptor.getValue();

        assertThat(capturedTodo.getName()).isEqualTo(savedTodo.getName());
        assertThat(capturedTodo.getStatus()).isEqualTo(savedTodo.getStatus());
    }

    @Test
    void it_should_throw_exception_when_todo_is_already_exists() {
        //given
        TodoRequest request = new TodoRequest();
        request.setName("todo-name");
        request.setStatus(TodoStatus.NOT_COMPLETED);

        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoRepository.findByName(request.getName())).thenReturn(Optional.of(response));

        //when
        Throwable throwable = catchThrowable(() -> todoService.save(request));

        //then
        assertThat(throwable).isNotNull().isInstanceOf(BusinessException.class);

        BusinessException exception = (BusinessException) throwable;
        assertThat(exception.getKey()).isEqualTo("todo.is.already.exists");

        verifyNoMoreInteractions(todoRepository);
    }

    @Test
    void it_should_get_all_todos() {
        //given

        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoRepository.findAll()).thenReturn(List.of(response));

        //when
        List<Todo> todos = todoService.findAll();

        //then
        assertThat(todos.size()).isEqualTo(1);
        assertThat(todos.get(0).getName()).isEqualTo(response.getName());
        assertThat(todos.get(0).getStatus()).isEqualTo(response.getStatus());
    }

    @Test
    void it_should_get_all_completed_todos() {
        //given

        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.COMPLETED);

        when(todoRepository.findAllByStatus(TodoStatus.COMPLETED)).thenReturn(List.of(response));

        //when
        List<Todo> todos = todoService.findAllCompleted();

        //then
        assertThat(todos.size()).isEqualTo(1);
        assertThat(todos.get(0).getName()).isEqualTo(response.getName());
        assertThat(todos.get(0).getStatus()).isEqualTo(TodoStatus.COMPLETED);
    }

    @Test
    void it_should_get_all_not_completed_todos() {
        //given
        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoRepository.findAll()).thenReturn(List.of(response));

        //when
        List<Todo> todos = todoService.findAll();

        //then
        assertThat(todos.size()).isEqualTo(1);
        assertThat(todos.get(0).getName()).isEqualTo(response.getName());
        assertThat(todos.get(0).getStatus()).isEqualTo(TodoStatus.NOT_COMPLETED);
    }

    @Test
    void it_should_delete_todo() {
        //given
        Long id=123L;

        //when
        todoService.delete(id);

        //then
        verify(todoRepository).deleteById(id);
    }

    @Test
    void it_should_delete_all_todos() {
        //given

        //when
        todoService.deleteAll();

        //then
        verify(todoRepository).deleteAll();
    }

    @Test
    void it_should_delete_all_completed_todos() {
        //given

        //when
        todoService.deleteAllCompleted();

        //then
        verify(todoRepository).deleteByStatus(TodoStatus.COMPLETED);
    }

    @Test
    void it_should_throw_exception_when_todo_is_not_found() {
        //given
        Long id=123L;

        TodoRequest request = new TodoRequest();
        request.setName("todo-name");
        request.setStatus(TodoStatus.NOT_COMPLETED);

        Todo response = new Todo();
        response.setName("todo-name");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> todoService.update(id, request));

        //then
        assertThat(throwable).isNotNull().isInstanceOf(DomainNotFoundException.class);

        DomainNotFoundException exception = (DomainNotFoundException) throwable;
        assertThat(exception.getKey()).isEqualTo("todo.is.not.found");

        //then
        verifyNoMoreInteractions(todoRepository);
    }

    @Test
    void it_should_update_todo() {
        //given
        Long id=123L;

        TodoRequest request = new TodoRequest();
        request.setName("todo-name_new");
        request.setStatus(TodoStatus.NOT_COMPLETED);

        Todo response = new Todo();
        response.setName("todo-name-old");
        response.setStatus(TodoStatus.NOT_COMPLETED);

        when(todoRepository.findById(id)).thenReturn(Optional.of(response));

        //when
        Todo todoResponse = todoService.update(id, request);

        //then
        ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository).save(todoArgumentCaptor.capture());
        Todo capturedTodo = todoArgumentCaptor.getValue();

        assertThat(capturedTodo.getName()).isEqualTo("todo-name_new");
        assertThat(capturedTodo.getStatus()).isEqualTo(TodoStatus.NOT_COMPLETED);
    }

}
