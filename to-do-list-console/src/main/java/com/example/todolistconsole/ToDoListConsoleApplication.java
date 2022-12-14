package com.example.todolistconsole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ToDoListConsoleApplication implements CommandLineRunner {

	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(ToDoListConsoleApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(context.getBean(ToDoListService.class).getItems());
	}

	@Bean
	RestTemplate restTemplate(@Value("${todo-service.url}") String url) {
		return new RestTemplateBuilder().rootUri(url).build();
	}

	@Autowired
	public void context(ApplicationContext context) {
		this.context = context;
	}
}
