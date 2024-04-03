package com.SCAI.ecommerce.service;

import com.SCAI.ecommerce.model.Product;

import java.util.List;

// Service: Business logic / Service Layer. Si collega ai controller.

// Questo blocco di codice definisce l'interfaccia ProductService,
// che rappresenta il livello di servizio o business logic dell'applicazione di e-commerce.
// L'interfaccia dichiara metodi per gestire le operazioni CRUD (Create, Read, Update, Delete) sui prodotti,
// oltre a metodi per la ricerca di prodotti basata su vari criteri come categoria, genere, colore, prezzo e nome.
// Include anche un metodo per generare un codice unico per ogni prodotto (codiceGenerator)
// e metodi che richiedono un token JWT per autorizzare alcune operazioni
// come l'inserimento, l'eliminazione e l'aggiornamento dei prodotti.
// Questa interfaccia serve da contratto per le classi di servizio
// che implementeranno la logica di business effettiva dietro queste operazioni.
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

    public List<Product> findByAccessorio(String accessorio) throws Exception;
}
