package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student luca = new Student(
                    "Luca Maita",
                    "lucamaita@gmail.com",
                    LocalDate.of(2001, JANUARY, 18),
                    23
            );

            Student alex = new Student(
                    "Alex Mercer",
                    "alexmercer@gmail.com",
                    LocalDate.of(2004, MAY, 4),
                    23
            );

            repository.saveAll(
                    List.of(luca, alex)
            );
        };
    }
}
