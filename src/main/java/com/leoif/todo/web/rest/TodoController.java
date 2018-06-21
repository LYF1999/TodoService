package com.leoif.todo.web.rest;


import com.leoif.todo.domain.Todo;
import com.leoif.todo.repository.TodoRepo;
import com.leoif.todo.security.SecurityUtils;
import com.leoif.todo.service.TodoService;
import com.leoif.todo.web.rest.errors.InternalServerErrorException;
import io.undertow.util.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/todo")
public class TodoController {

    @Autowired
    TodoRepo todoRepo;

    @Autowired
    TodoService todoService;

    @GetMapping(value = "")
    List<Todo> list() {
        return this.todoRepo.findAllByUsername(SecurityUtils.getCurrentUserLogin().orElseThrow(
            () -> new InternalServerErrorException("not found user")
        ));
    }

    @PostMapping(value = "")
    Todo create(@RequestBody Todo todo) throws BadRequestException {
        todo.username = SecurityUtils.getCurrentUserLogin().orElseThrow(
            () -> new InternalServerErrorException("not found user"));
        return todoService.save(todo);
    }

    @PatchMapping(value = "/{id}")
    Todo update(@PathVariable String id, @RequestBody Todo newTodo) {
        Todo todo = todoRepo.findOne(Long.parseLong(id));
        todo.description = newTodo.description;
        todo.finished = newTodo.finished;
        todoRepo.save(todo);
        return todo;
    }

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable Long id) {
        todoRepo.delete(id);
    }
}
