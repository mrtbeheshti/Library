package com.example.library;


//TODO: Connect To Database
//TODO: Pathname Editing
//TODO: Change the port from 8080 to another port
//TODO: Use customize Exceptions for errors
//TODO: Use multilayer architecture like: User-> Controller-> Service-> Repository-> DB
//TODO: Use branches for commites in each task
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
