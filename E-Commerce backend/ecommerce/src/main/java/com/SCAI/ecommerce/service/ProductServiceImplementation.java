package com.SCAI.ecommerce.service;

import com.SCAI.ecommerce.repository.ProductRepository;
import com.SCAI.ecommerce.model.User;
import com.SCAI.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Questo blocco di codice implementa ProductService,
// fornendo la logica di business per la gestione dei prodotti.
// La classe ProductServiceImplementation utilizza ProductRepository per interagire con il database
// e UserService per gestire le autorizzazioni basate sui ruoli degli utenti.
// Include metodi per inserire, trovare, eliminare e aggiornare prodotti,
// oltre a metodi per cercare prodotti basati su categoria, genere, colore, prezzo e nome.
// La generazione del codice articolo unico per ogni prodotto viene gestita tramite codiceGenerator,
// che combina caratteristiche del prodotto con un contatore incrementale.
// L'accesso a molte di queste funzionalità è limitato agli utenti con ruolo di amministratore,
// come indicato dalla verifica del ruolo nell'inserimento, eliminazione e aggiornamento dei prodotti.

@Service // Service indica che una classe e un componente di un servizio all'interno dell'app
public class ProductServiceImplementation implements ProductService {

    private static int ultimoCodice = 0;

    public static String codiceStringa() {
        ultimoCodice++;
        return String.format("%03d", ultimoCodice % 1000);
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;


    @Override
    public Product insertProduct(Product product, String jwt) throws Exception {

        User u = userService.findUserByJwt(jwt);

        if (!u.getRole().equals(User.Role.ADMIN)){
            throw new Exception("ADMIN PRIVILEGES REQUIRED");
        }

        Product insertedProduct = new Product();
        insertedProduct.setNome(product.getNome());
        insertedProduct.setDescrizione(product.getDescrizione());
        insertedProduct.setFoto(product.getFoto());
        insertedProduct.setCategoria(product.getCategoria());
        insertedProduct.setPrezzo(product.getPrezzo());
        insertedProduct.setColore(product.getColore());
        insertedProduct.setTaglia(product.getTaglia());
        insertedProduct.setGenere(product.getGenere());
        insertedProduct.setAccessorio(product.getAccessorio());
        insertedProduct.setPeso(product.getPeso());
        insertedProduct.setQuantita(product.getQuantita());
        insertedProduct.setCodice_articolo(codiceGenerator(product));
        insertedProduct.setDataInserimento(LocalDate.now());
        return productRepository.save(insertedProduct);
    }

    @Override
    public String codiceGenerator(Product product) {
        StringBuilder codice = new StringBuilder();
        codice.append(product.getCategoria().charAt(0));
        codice.append(product.getNome().charAt(0));
        codice.append(product.getNome().toUpperCase().charAt(1));
        codice.append(product.getNome().toUpperCase().charAt(2));
        codice.append(product.getColore().charAt(0));
        codice.append(product.getTaglia());
        codice.append(codiceStringa());
        return codice.toString();
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
    public void deleteProduct(Long id, String jwt) throws Exception {

        User u = userService.findUserByJwt(jwt);

        if (!u.getRole().equals(User.Role.ADMIN)){
            throw new Exception("ADMIN PRIVILEGES REQUIRED");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product, Long id, String jwt) throws Exception {

        User u = userService.findUserByJwt(jwt);

        if (!u.getRole().equals(User.Role.ADMIN)){
            throw new Exception("ADMIN PRIVILEGES REQUIRED");
        }

        Product oldProduct = findProductById(id);

        if(product.getNome()!=null){
            oldProduct.setNome(product.getNome());
        }
        if(product.getDescrizione()!=null){
            oldProduct.setDescrizione(product.getDescrizione());
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
        if(product.getGenere()!=null){
            oldProduct.setGenere(product.getGenere());
        }
        if(product.getAccessorio()!=null){
            oldProduct.setAccessorio(product.getAccessorio());
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

    @Override
    public List<Product> findByCategoria(String categoria) throws Exception {
        List<Product> products = productRepository.findByCategoria(categoria);

        if(products.isEmpty()){
            throw new Exception("Prodotto non trovato con Categoria: " + categoria);
        }else{
            return products;
        }
    }

    @Override
    public List<Product> findByGenere(String genere) throws Exception {
        List<Product> products = productRepository.findByGenere(genere);

        if(products.isEmpty()){
            throw new Exception("Prodotto non trovato per genere: " + genere);
        }else{
            return products;
        }
    }

    @Override
    public List<Product> findByColore(String colore) throws Exception {
        List<Product> products = productRepository.findByColore(colore);

        if(products.isEmpty()){
            throw new Exception("Prodotto non trovato per colore: " + colore);
        }else{
            return products;
        }
    }

    @Override
    public List<Product> ricercaPrezzo(Integer min, Integer max) throws Exception {
        List<Product> products = productRepository.findAll();
        List<Product> abbordabili = new ArrayList<>();
        for(Product p : products){
            if (p.getPrezzo() > min && p.getPrezzo() < max){
                abbordabili.add(p);
            }
        }
        return abbordabili;
    }

    @Override
    public List<Product> findByNome(String nome) throws Exception {
//        List<Product> products = productRepository.findByNome(nome);
//
//        if(products.isEmpty()){
//            throw new Exception("Prodotto non trovato per nome: " + nome);
//        }else{
//            return products;
//        }
        List<Product> products = productRepository.findAll();
        List<Product> corrispondenze = new ArrayList<>();
        for(Product p : products){
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())){
                corrispondenze.add(p);
            }
        }
        return corrispondenze;
    }

    @Override
    public List<Product> findByAccessorio(String accessorio) throws Exception {
        List<Product> products = productRepository.findByAccessorio(accessorio);

        if(products.isEmpty()){
            throw new Exception("Prodotto non trovato per colore: " + accessorio);
        }else{
            return products;
        }
    }

}
