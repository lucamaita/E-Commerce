package com.maita.prova.service;

import com.maita.prova.model.Product;
import com.maita.prova.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service // Service indica che una classe e un componente di un servizio all'interno dell'app
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product insertProduct(Product product) {

        Product insertedProduct = new Product();
        insertedProduct.setNome(product.getNome());
        insertedProduct.setFoto(product.getFoto());
        insertedProduct.setCodice_articolo(product.getCodice_articolo());
        insertedProduct.setCategoria(product.getCategoria());
        insertedProduct.setPrezzo(product.getPrezzo());
        insertedProduct.setColore(product.getColore());
        insertedProduct.setTaglia(product.getTaglia());
        insertedProduct.setPeso(product.getPeso());
        insertedProduct.setQuantita(product.getQuantita());
        insertedProduct.setDataInserimento(LocalDate.now());
        return productRepository.save(insertedProduct);
    }

    @Override
    public Product findProductById(Long id) throws Exception {
        //OPTIONAL: la ricerca puo andare a buon fine oppure no
        Optional<Product> opt = productRepository.findById(id);

        if(opt.isPresent()){
            return opt.get();
        }

        throw new Exception("Prodotto non trovato con ID: " + id);
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        findProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product, Long id) throws Exception {
        Product oldProduct = findProductById(id);

        if(product.getNome()!=null){
            oldProduct.setNome(product.getNome());
        }
        if(product.getFoto()!=null){
            oldProduct.setFoto(product.getFoto());
        }
        if(product.getCodice_articolo()!=null){
            oldProduct.setCodice_articolo(product.getCodice_articolo());
        }
        if(product.getCategoria()!=null){
            oldProduct.setCategoria(product.getCategoria());
        }
        if(product.getPrezzo()!=null){
            oldProduct.setPrezzo(product.getPrezzo());
        }
        if(product.getColore()!=null){
            oldProduct.setColore(product.getColore());
        }
        if(product.getTaglia()!=null){
            oldProduct.setTaglia(product.getTaglia());
        }
        if(product.getPeso()!=null){
            oldProduct.setPeso(product.getPeso());
        }
        if(product.getQuantita()!=null){
            oldProduct.setQuantita(product.getQuantita());
        }
        if(product.getDataInserimento()!=null){
            oldProduct.setDataInserimento((product.getDataInserimento()));
        }

        return productRepository.save(oldProduct);
    }

    @Override
    public List<Product> findAllProducts() {

        return productRepository.findAll();
    }

}
