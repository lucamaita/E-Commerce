package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repo deve estendere JPARepo, che estende PagingAndSortingRepo, che estende CRUDRepo
// Bisogna passare il tipo di dato (Student) e il tipo dell'id (Long)
@Repository // Interfaccia responsabile dell'accesso ai dati
public interface StudentRepository extends JpaRepository<Student, Long> {
}
