package com.example.firstapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FirstapiApplication {

	String property = "unset";

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String sayHello(){

		return "Hallo, Esslingen. Beautiful weather in October!";
	}

	@PostMapping("/test/{variable}")
	public String postVariable(@PathVariable String variable){

		property = variable;

		return property;
	}

	@GetMapping("/test")
	public String outputProperty(){

		return property;
	}

	@GetMapping("/hi")
	public String sayHelloAgain(){

		return "Hello again, Esslingen. Beautiful weather in October!";
	}

	public static void main(String[] args) {
		SpringApplication.run(FirstapiApplication.class, args);
	}

}
