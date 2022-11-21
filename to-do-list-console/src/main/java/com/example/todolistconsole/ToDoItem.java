package com.example.todolistconsole;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class ToDoItem {
    int ID;
    String description;
}
