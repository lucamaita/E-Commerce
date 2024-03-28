package com.SCAI.ecommerce.service;

import com.SCAI.ecommerce.model.Product;

import java.util.List;

// Service: Business logic / Service Layer. Si collega ai controller.
public interface ProductService {

    public Product insertProduct(Product product, String jwt) throws Exception;
    public String codiceGenerator(Product product);
    public Product findProductById(Long id) throws Exception;
    public void deleteProduct(Long id, String jwt) throws Exception;
    public Product updateProduct(Product product, Long id, String jwt) throws Exception;
    public List<Product> findAllProducts();
    public List<Product> findByCategoria(String categoria) throws Exception;
    public List<Product> findByGenere(String genere) throws Exception;
    public List<Product> findByColore(String colore) throws Exception;
    public List<Product> ricercaPrezzo(Integer min, Integer max) throws Exception;
    public List<Product> findByNome(String nome) throws Exception;
}
