package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class TestRestClient {

    @GetMapping("/testRestList")
    public String testRestList(){

        return WebClient.create("https://8080-maeddes-hse2022winter-oz2p2rbi8vt.ws-eu74.gitpod.io/todos/")
        .get()
        .retrieve()
        .bodyToMono(TodoItem[].class)
        .block()
        .toString();

    }

    @GetMapping("/testGetRestObject")
    public String testRestObject(){

        return WebClient.create("https://8080-maeddes-hse2022winter-oz2p2rbi8vt.ws-eu74.gitpod.io/todos/one")
        .get()
        .retrieve()
        .bodyToMono(TodoItem.class)
        .block()
        .toString();

    }

    @GetMapping("/testPostRestObject")
    public TodoItem testPostRestObject(){

        return WebClient
            .create("https://8080-maeddes-hse2022winter-oz2p2rbi8vt.ws-eu74.gitpod.io/todos/one")
            .post()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(new TodoItem("one")), TodoItem.class)
            .retrieve()
            .bodyToMono(TodoItem.class)
            .block();

    }
    
}
