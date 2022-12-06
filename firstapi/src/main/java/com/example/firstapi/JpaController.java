package com.example.firstapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v2/todos")
public class JpaController {

    @Autowired
    TodoItemRepository todoItemRepository;

    @ApiResponses(value = 
    {
        @ApiResponse(responseCode = "201", description = "Item has been created" , content = @Content)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}")
    public TodoItem createAndAddTodoItem(@PathVariable String name){

        TodoItem item = new TodoItem(name);
        todoItemRepository.save(item);

        return item;
    }

    // TODO implement other CRUD methods and make API compliant to v1

    @GetMapping("/count")
    public long getAmountOfItems(){

        //todoItemRepository.
        return todoItemRepository.count();

    }

    @GetMapping("/id/{id}")
    public Optional<TodoItem> findById(@PathVariable String id){

        return todoItemRepository.findById(id);

    }

    // List all elements in ArrayList
    @Operation(summary = "Returns a list of ToDo items")
    @GetMapping(value = "/", produces = "application/json")
    @ApiResponses(value = 
                    {
                        @ApiResponse(responseCode = "200", description = "List all items" , content = @Content)
                    })
    @ResponseStatus(HttpStatus.OK)
    public List<TodoItem> getTodoItems(){

        return todoItemRepository.findAll();
    }



    
}
