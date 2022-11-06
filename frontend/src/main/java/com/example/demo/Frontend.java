package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("/")
public class Frontend {

    @Value("${backend.endpoint}")
    private String todoApiEndpoint;

    @GetMapping
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


    
}
