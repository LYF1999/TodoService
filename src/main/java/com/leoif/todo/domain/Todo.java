package com.leoif.todo.domain;


import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public String description;


    public Boolean finished = false;
    public String username;

    @CreatedDate
    public Instant time = Instant.now();
}
