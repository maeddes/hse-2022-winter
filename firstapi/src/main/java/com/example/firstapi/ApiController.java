package com.example.firstapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


// CRUD Controller class
@RestController
@RequestMapping("/api/v2/items")
public class ApiController {

    private ArrayList<TodoItem> items = new ArrayList<TodoItem>();

    @GetMapping("/version")
    public String getVersion(){

        return "v2";
    }

    // Add new item to list
    // version 1: using path variables
    @Operation(summary = "Creates a Todo Item with path variable name and default priority of 2")
    @ApiResponses(value = 
    {
        @ApiResponse(responseCode = "201", description = "Item has been created" , content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public TodoItem createAndAddItem(@PathVariable String name){

        TodoItem item = new TodoItem(name);
        items.add(item);

        return item;
    }

    // Add new item to list
    // version 2: using a JSON object as input

    // Be careful when importing RequestBody
    @Operation(summary = "Creates a Todo Item with a JSON object as request paramter")
    @ApiResponses(value = 
    {
        @ApiResponse(responseCode = "201", description = "Item has been created" , content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public TodoItem addItem(@RequestBody TodoItem item){

        items.add(item);
        return item;
    }

    // List all elements in ArrayList
    @Operation(summary = "Returns a list of ToDo items")
    @GetMapping(value = "/", produces = "application/json")
    @ApiResponses(value = 
                    {
                        @ApiResponse(responseCode = "200", description = "List all items" , content = @Content)
                    })
    @ResponseStatus(HttpStatus.OK)
    public List<TodoItem> getItems(){

        return items;
    }

    @Operation(summary = "Find a shopping item by its itemId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the item", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid itemId supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content) })
    @GetMapping(produces = "application/json", path = "/{itemId}")
    Optional<TodoItem> getShoppingItem(@PathVariable long itemId) {

        return null;

    }

    // Update an item in the list

    // your TODO :-)

    // Delete an item from the list
    
}