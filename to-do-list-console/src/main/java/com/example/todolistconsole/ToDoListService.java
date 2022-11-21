package com.example.todolistconsole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ToDoListService {

    private final RestTemplate restTemplate;

    @Autowired
    public ToDoListService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ToDoItem> getItems() {
        ToDoItem[] items = restTemplate.getForObject("/items", ToDoItem[].class);

        return items == null ? new ArrayList<>() : Arrays.asList(items);
    }
}
