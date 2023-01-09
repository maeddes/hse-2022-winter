package com.example.firstapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
// makes class eligible to handle HTTP requests
@RestController
@RequestMapping("/practice")
public class FirstapiApplication {

	String property = "unset";
	String newprop = "";
	TodoItem item = new TodoItem("test");

	@Value("${hostname}")
	private String hostname;

	/**
	 * @return nothing
	 */
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }



	// map base URL to method sayHello()
	@RequestMapping(method = RequestMethod.GET, path = "/requeststring")
	public String simpleRequestMappingGetHello(){

		return "Hallo, Esslingen. Beautiful weather in October!";
	}

	@GetMapping(path = "/hello")
	public String simpleGetMappingHello(){

		return hostname+"Hallo, Esslingen. Beautiful weather in October!";
	}

	@GetMapping("/fail")
	public String criticalMethod(){

		// problem fixed System.exit(1);
		return "can be reached now";
	}

	/*
	 * Remember, we don't need to annotate the @RestController-annotated controllers 
	 * with the @ResponseBody annotation since Spring does it by default.
	 */

	@GetMapping(path = "/getstringresponsebody")
	@ResponseBody
	public String simpleGetMappingHelloWithResponseBody(){

		return "Hallo, Esslingen. Beautiful weather in October!";
	}

	@GetMapping(path = "/getobject")
	public String simpleGetMappingObject(){

		return "Hallo, Esslingen. Beautiful weather in October!";
	}

	@PostMapping("/test/{variable}")
	public String postVariable(@PathVariable String variable){

		property = variable;
		return property;
		
	}

	// should not be like that - GET is supposed to be implemented read-only
	// example of how you should not do is - this should be a PUT
	@GetMapping("fakeGet/{parameter}")
	public String fakeGet(@PathVariable String parameter){

		property = parameter;
		return "Local property is: "+property;
	}

	@PostMapping("/users/{name}/{lastname}")
	public Object createUser(){

		// new User(name, lastname);

		return null;
	}

	@GetMapping("/test")
	public String outputProperty(){

		return property;
	}

	@GetMapping("/xxx")
	public String sayHelloAgain(){

		return "Hello again, Esslingen. Beautiful weather in October!";
	}

	public static void main(String[] args) {
		SpringApplication.run(FirstapiApplication.class, args);
	}

	// Richardson's Maturity Model

	// // The swamp of pox
	// @GetMapping("/deleteLastNamefromObject/{variable}")

	// // Using resources/objects
	// @GetMapping("/Object/{object_properties}")

	// // Using resources/objects/verbs
	// @PutMapping("/Object/{id}/{object_properties}")



}
