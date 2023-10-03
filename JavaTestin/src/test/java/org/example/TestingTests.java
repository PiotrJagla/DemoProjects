package org.example;

import org.example.testClasses.TodoService;
import org.example.testClasses.TodoServiceImpl;
import org.example.testClasses.TodobusinessImpl;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestingTests {

    @Test
    public void test1() {
        TodoService todoService = mock(TodoService.class);
        when(todoService.fetchTodos("Dummy")).thenReturn(List.of("Spring", "why is this Spring"));

        TodobusinessImpl todobusiness = new TodobusinessImpl(todoService);

        assertEquals(2, todobusiness.fetch("Dummy").size());
    }

}
