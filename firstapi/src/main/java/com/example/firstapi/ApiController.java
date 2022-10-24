package com.example.firstapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// CRUD Controller class
@RestController
@RequestMapping("/api-v2")
public class ApiController {

    private ArrayList<TodoItem> items = new ArrayList<TodoItem>();

    @GetMapping("/version")
    public String getVersion(){

        return "v2";
    }

    // Add new item to list
    // version 1: using path variables
    // version 2: using a JSON object as input

    @PostMapping("/items/{name}")
    public TodoItem createAndAddItem(@PathVariable String name){

        TodoItem item = new TodoItem(name);
        items.add(item);

        return item;
    }

    @GetMapping("/items/")
    public List<TodoItem> getItems(){

        return items;
    }



    // List all elements in ArrayList


    // Update an item in the list

    // Delete an item from the list
    
}
