package com.SCAI.ecommerce.repository;

import com.SCAI.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoria(String categoria);
    List<Product> findByGenere(String genere);
    List<Product> findByColore(String colore);

    //List<Product> ricercaPrezzo(Integer min, Integer max);

    //List<Product> findByNome(String nome);
}
