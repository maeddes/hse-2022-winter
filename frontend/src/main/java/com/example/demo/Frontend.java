package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Controller
public class Frontend {

    @Value("${backend.endpoint}")
    private String todoApiEndpoint;

    @GetMapping("/")
    public String displayPage(Model model) {

        TodoItem[] items = WebClient
                .create(todoApiEndpoint)
                .get()
                .retrieve()
                .bodyToMono(TodoItem[].class)
                .block();

        model.addAttribute("items", items);

        return "page";
    }

    @PostMapping("/create")
	public String addItem(@RequestParam String newItem, @RequestParam int priority, Model model){

        TodoItem item = new TodoItem();
        item.todo = newItem;
        item.priority = priority;

        WebClient
            .create(todoApiEndpoint)
            .post()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(item), TodoItem.class)
            .retrieve()
            .bodyToMono(TodoItem.class)
            .block();

        return "redirect:/";
	}

    @PostMapping("/delete")
    public String deleteItem(@RequestParam String todo){

        WebClient
            .create(todoApiEndpoint+todo)
            .delete()
            .retrieve()
            .bodyToMono(Void.class)
            .block();

        return "redirect:/";

    }
    
}
