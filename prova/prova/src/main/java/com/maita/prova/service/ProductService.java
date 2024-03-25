package com.maita.prova.service;

import com.maita.prova.model.Product;

import java.util.List;

// Service: Business logic / Service Layer. Si collega ai controller.
public interface ProductService {

    public Product insertProduct(Product product);
    public String codiceGenerator(Product product);
    public Product findProductById(Long id) throws Exception;
    public void deleteProduct(Long id) throws Exception;
    public Product updateProduct(Product product, Long id) throws Exception;
    public List<Product> findAllProducts();
    public List<Product> findByCategoria(String categoria) throws Exception;
    public List<Product> findByGenere(String genere) throws Exception;
    public List<Product> findByColore(String colore) throws Exception;
}
