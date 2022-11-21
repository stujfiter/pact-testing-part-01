package com.example.todolistapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ToDoListController {

    @GetMapping("items")
    public List<ToDoItem> getAllItems() {
        return Arrays.asList(
                new ToDoItem(1, "clean house"),
                new ToDoItem(2, "wash car"));
    }
}
