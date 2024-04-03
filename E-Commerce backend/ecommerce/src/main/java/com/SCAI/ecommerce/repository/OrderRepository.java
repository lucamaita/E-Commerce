package com.SCAI.ecommerce.repository;

import com.SCAI.ecommerce.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
// Vedi commento in UserRepository
public interface OrderRepository extends JpaRepository<Ordine, Long> {
}
