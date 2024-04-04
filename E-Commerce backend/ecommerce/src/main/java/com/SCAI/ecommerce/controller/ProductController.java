package com.SCAI.ecommerce.controller;

import com.SCAI.ecommerce.model.Product;
import com.SCAI.ecommerce.model.ProdottiAggregati;
import com.SCAI.ecommerce.service.ProductService;
import com.SCAI.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ProductController: gestisce le operazioni CRUD e le ricerche sui prodotti in un'applicazione di e-commerce.
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

    @GetMapping("/products/getAllFe")
    public List<ProdottiAggregati> getAllProductsAggregated() throws Exception {
        List<Product> products = productService.findAllProducts();

        Map<String, ProdottiAggregati> aggregatedProducts = new HashMap<>();

        for (Product product : products) {
            String key = product.getNome() + "-" + product.getDescrizione();

            if (aggregatedProducts.containsKey(key)) {
                ProdottiAggregati aggregatedProduct = aggregatedProducts.get(key);
                aggregatedProduct.addTaglie(product.getTaglia());
                aggregatedProduct.addColore(product.getColore());
            } else {
                ProdottiAggregati newAggregatedProduct = new ProdottiAggregati(product);
                aggregatedProducts.put(key, newAggregatedProduct);
            }
        }

        return new ArrayList<>(aggregatedProducts.values());
    }

    @GetMapping("/products/getAllFe/{gender}")
    public List<ProdottiAggregati> getAllProductsByGender(@PathVariable String gender) throws Exception {
        List<Product> products = productService.findAllProducts();

        Map<String, ProdottiAggregati> aggregatedProducts = new HashMap<>();

        for (Product product : products) {
            if (!gender.equalsIgnoreCase(product.getGenere())) {
                continue;
            }

            String key = product.getNome() + "-" + product.getDescrizione();

            if (aggregatedProducts.containsKey(key)) {
                ProdottiAggregati aggregatedProduct = aggregatedProducts.get(key);
                aggregatedProduct.addTaglie(product.getTaglia());
                aggregatedProduct.addColore(product.getColore());
            } else {
                ProdottiAggregati newAggregatedProduct = new ProdottiAggregati(product);
                aggregatedProducts.put(key, newAggregatedProduct);
            }
        }

        return new ArrayList<>(aggregatedProducts.values());
    }


    @DeleteMapping("/api/products/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId,
                                @RequestHeader("Authorization") String jwt)throws Exception{

            productService.deleteProduct(productId, jwt);
            return "Product Deleted Successfully";
    }

    @GetMapping("/products/findByCategoria/{categoria}")
    public List<Product> findByCategoria(@PathVariable String categoria) throws Exception{
        List<Product> products = productService.findByCategoria(categoria);
        return products;
    }

    @GetMapping("/products/findByGenere/{genere}")
     public List<Product> findByGenere(@PathVariable String genere) throws Exception {
        List<Product> products = productService.findByGenere(genere);
        return products;
    }

    @GetMapping("/products/findByColore/{colore}")
    public List<Product> findByColore(@PathVariable String colore) throws Exception {
        List<Product> products = productService.findByColore(colore);
        return products;
    }

    @GetMapping("/products/findByPrezzo/{min},{max}")
    public List<Product> ricercaPrezzo(@PathVariable Integer min, @PathVariable Integer max) throws Exception {
        List<Product> products = productService.ricercaPrezzo(min, max);
        return products;
    }

    @GetMapping("/products/findByNome/{nome}")
    public List<Product> ricercaNome(@PathVariable String nome) throws Exception{
        List<Product> products = productService.findByNome(nome);
        return products;
    }

    @GetMapping("/products/findByAccessorio/{accessorio}")
    public List<Product> ricercaAccessorio(@PathVariable String accessorio) throws Exception{
        List<Product> products = productService.findByAccessorio(accessorio);
        return products;
    }

}


