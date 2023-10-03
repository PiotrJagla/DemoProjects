package org.example.testClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodobusinessImpl {
    private TodoService todoService;

    public TodobusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> fetch(String user) {
        return todoService.fetchTodos(user)
                .stream().filter(t -> t.contains("Spring"))
                .collect(Collectors.toList());
    }
}
