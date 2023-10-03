package org.example.testClasses;

import java.util.Arrays;
import java.util.List;

public interface TodoService{
    List<String> fetchTodos(String user);
}
