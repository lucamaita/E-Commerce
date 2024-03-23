package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Classe Controller: API layer, gestisce le richieste HTTP e fornisce le conseguenti risposte
// una volta ricevuta la richiesta si interfaccia con il service per elaborare le richieste tramite la buisness logic
@RestController // RestController: i metodi del controller resituiranno direttamente i dati serializzati come risposte HTTP invece di HTML
@RequestMapping(path = "api/v1/students") // RequestMapping: Mappa le richieste HTTP a specifici metodi di un controller
public class StudentController {

    private final StudentService studentService;

    @Autowired // Autowired permette l'iniezione automatica delle dipendenze (instanzia i bean necessari per l'iniezione)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping // GetMapping: mappa le richieste HTTP di tipo GET a dei metodi di un controller
    public List<Student> getStudents() {
    return studentService.getStudents();
    }
}
