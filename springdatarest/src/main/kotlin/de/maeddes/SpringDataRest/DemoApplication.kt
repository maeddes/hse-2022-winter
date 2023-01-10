package de.maeddes.springdatarest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.RestController

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.RequestMapping

@SpringBootApplication
@RestController
class DemoApplication ()

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}

@Entity
class TodoItem(
        @Id
        var todo: String,
		val priority: Int)

@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
interface TodoItemRepository : CrudRepository<TodoItem, String> {}