package fi.hh.BookStore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;

import fi.hh.BookStore.domain.Book;
import fi.hh.BookStore.domain.BookRepository;
import fi.hh.BookStore.domain.User;
import fi.hh.BookStore.domain.UserRepository;

@SpringBootApplication
public class BookStoreApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner book(BookRepository repository, UserRepository userepository) {
		return (args) -> {
			repository.save(new Book("Lord of the Rings", "Tolkien", 2000, "asfsaf-asfsaa", 9.5));
			for (Book book : repository.findAll()) {
				
			}
			
			User user1 = new User("user",
                    "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            User user2 = new User("admin",
                    "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
            userepository.save(user1);
            userepository.save(user2);
		};
	}
	
}