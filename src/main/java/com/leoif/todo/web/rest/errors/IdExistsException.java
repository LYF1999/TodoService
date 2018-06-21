package com.leoif.todo.web.rest.errors;

import io.undertow.util.BadRequestException;

public class IdExistsException extends BadRequestException {
    public IdExistsException() {
        super("id exists");
    }
}
