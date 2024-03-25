package com.maita.prova.controller;

import com.maita.prova.model.Product;
import com.maita.prova.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products") // RequestMapping: Mappa le richieste HTTP a specifici metodi di un controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product){
        Product insertedProduct = productService.insertProduct(product);
        return insertedProduct;
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product,
                                 @PathVariable Long id) throws Exception{
        Product updatedProduct = productService.updateProduct(product, id);
        return updatedProduct;
    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = productService.findAllProducts();
        return products;
    }

    @DeleteMapping("/cercaId/{productId}")
    public String deleteProduct(@PathVariable Long productId) throws Exception{
        productService.deleteProduct(productId);
        return "Product Deleted Successfully";
    }

    @GetMapping("/cercaCategoria/{categoria}")
    public List<Product> findByCategoria(@PathVariable String categoria) throws Exception{
        List<Product> products = productService.findByCategoria(categoria);
        return products;
    }

    @GetMapping("/cercaGenere/{genere}")
     public List<Product> findByGenere(@PathVariable String genere) throws Exception {
        List<Product> products = productService.findByGenere(genere);
        return products;
    }

    @GetMapping("/cercaColore/{colore}")
    public List<Product> findByColore(@PathVariable String colore) throws Exception {
        List<Product> products = productService.findByColore(colore);
        return products;
    }
    @GetMapping("/ricercaPrezzo/{min},{max}")
    public List<Product> ricercaPrezzo(@PathVariable Integer min, @PathVariable Integer max) throws Exception {
        List<Product> products = productService.ricercaPrezzo(min, max);
        return products;
    }

    @GetMapping("/cercaNome/{nome}")
    public List<Product> ricercaNome(@PathVariable String nome) throws Exception{
        List<Product> products = productService.findByNome(nome);
        return products;
    }

}
