package com.maita.prova.repository;

import com.maita.prova.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Repo deve estendere JPARepo, che estende PagingAndSortingRepo, che estende CRUDRepo
// Bisogna passare il tipo di dato (Student) e il tipo dell'id (Long)
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
