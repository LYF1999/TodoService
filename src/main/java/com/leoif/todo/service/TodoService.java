package com.leoif.todo.service;

import com.leoif.todo.domain.Todo;
import com.leoif.todo.repository.TodoRepo;
import com.leoif.todo.web.rest.errors.IdExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;

    public Todo save(Todo todo) throws IdExistsException {
        if(todo.getId() != null) throw new IdExistsException();
        todo.time = Instant.now();
        return todoRepo.save(todo);
    }
}
