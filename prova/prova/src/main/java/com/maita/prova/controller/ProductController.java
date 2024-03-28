package com.maita.prova.controller;

import com.maita.prova.model.Product;
import com.maita.prova.service.ProductService;
import com.maita.prova.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping // RequestMapping: Mappa le richieste HTTP a specifici metodi di un controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/products/insert")
    public Product insertProduct(@RequestBody Product product,
                                 @RequestHeader("Authorization") String jwt) throws Exception {
        Product insertedProduct = productService.insertProduct(product, jwt);
        return insertedProduct;
    }

    @PutMapping("/api/products/update/{id}")
    public Product updateProduct(@RequestBody Product product,
                                 @PathVariable Long id,
                                 @RequestHeader("Authorization") String jwt) throws Exception{
        Product updatedProduct = productService.updateProduct(product, id, jwt);
        return updatedProduct;
    }

    @GetMapping("/products/getAll")
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = productService.findAllProducts();
        return products;
    }

    @DeleteMapping("/api/products/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId,
                                @RequestHeader("Authorization") String jwt)throws Exception{

            productService.deleteProduct(productId, jwt);
            return "Product Deleted Successfully";
    }

    @GetMapping("/products/findBy/{categoria}")
    public List<Product> findByCategoria(@PathVariable String categoria) throws Exception{
        List<Product> products = productService.findByCategoria(categoria);
        return products;
    }

    @GetMapping("/products/findBy/{genere}")
     public List<Product> findByGenere(@PathVariable String genere) throws Exception {
        List<Product> products = productService.findByGenere(genere);
        return products;
    }

    @GetMapping("/products/findBy/{colore}")
    public List<Product> findByColore(@PathVariable String colore) throws Exception {
        List<Product> products = productService.findByColore(colore);
        return products;
    }
    @GetMapping("/products/findBy/{min},{max}")
    public List<Product> ricercaPrezzo(@PathVariable Integer min, @PathVariable Integer max) throws Exception {
        List<Product> products = productService.ricercaPrezzo(min, max);
        return products;
    }

    @GetMapping("/products/findBy/{nome}")
    public List<Product> ricercaNome(@PathVariable String nome) throws Exception{
        List<Product> products = productService.findByNome(nome);
        return products;
    }

}
