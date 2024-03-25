package com.maita.prova.repository;

import com.maita.prova.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordine, Long> {
}
