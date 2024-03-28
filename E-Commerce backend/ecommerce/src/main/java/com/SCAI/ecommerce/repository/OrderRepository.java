package com.SCAI.ecommerce.repository;

import com.SCAI.ecommerce.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordine, Long> {
}
