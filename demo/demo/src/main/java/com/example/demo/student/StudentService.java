package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Service: Business logic / Service Layer. Si collega ai controller.
@Service // Service indica che una classe e un componente di un servizio all'interno dell'app
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Luca",
                        "lucamaita@gmail.com",
                        LocalDate.of(2001, Month.JANUARY, 18),
                        23
                )
        );
    }
}
