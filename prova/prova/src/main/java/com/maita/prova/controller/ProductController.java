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

    @PostMapping
    public Product insertProduct(@RequestBody Product product){
        Product insertedProduct = productService.insertProduct(product);
        return insertedProduct;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product,
                                 @PathVariable Long id) throws Exception{
        Product updatedProduct = productService.updateProduct(product, id);
        return updatedProduct;
    }

    @GetMapping
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = productService.findAllProducts();
        return products;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) throws Exception{
        productService.deleteProduct(productId);
        return "Product Deleted Successfully";
    }
}
