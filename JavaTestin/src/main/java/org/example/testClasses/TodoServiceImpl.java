package org.example.testClasses;

import java.util.List;

public class TodoServiceImpl implements TodoService{
    @Override
    public List<String> fetchTodos(String user) {
        return List.of("Spring", "what is this", "this is a list", "why does it contain Spring", "i love programming");
    }
}
