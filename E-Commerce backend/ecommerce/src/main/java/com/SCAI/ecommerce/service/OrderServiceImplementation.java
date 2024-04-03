package com.SCAI.ecommerce.service;

import com.SCAI.ecommerce.repository.OrderRepository;
import com.SCAI.ecommerce.model.User;
import com.SCAI.ecommerce.model.Ordine;
import com.SCAI.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// Questo blocco di codice definisce OrderServiceImplementation, una classe che implementa l'interfaccia OrderService
// per gestire le operazioni relative agli ordini. La classe utilizza OrderRepository per interagire con il database degli ordini
// e UserService per gestire le autorizzazioni degli utenti.
// La classe è annotata con @Service, indicando che è un componente del livello di servizio dell'applicazione,
// responsabile per la logica di business. Gli oggetti OrderRepository e UserService sono iniettati tramite @Autowired,
// permettendo l'accesso ai metodi definiti in queste classi senza la necessità di istanziarli direttamente.
@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Ordine> findAll(String jwt) throws Exception {

        User u = userService.findUserByJwt(jwt);

        if (!u.getRole().equals(User.Role.ADMIN)){
            throw new Exception("ADMIN PRIVILEGES REQUIRED");
        }

        return orderRepository.findAll();
    }

    @Override
    public Ordine insertOrder(Ordine ordine, User user, Product product) {
        Ordine insertedOrdine = new Ordine();
        insertedOrdine.setUser(user);
        insertedOrdine.setProduct(product);
        insertedOrdine.setQuantita(ordine.getQuantita());
        insertedOrdine.setPrezzo(product.getPrezzo() * ordine.getQuantita());
        insertedOrdine.setStato(ordine.getStato());
        return orderRepository.save(insertedOrdine);
    }

    @Override
    public Ordine findOrderById(Long id) throws Exception {
        Optional<Ordine> opt = orderRepository.findById(id);

        if(opt.isPresent()){
            return opt.get();
        }

        throw new Exception("Ordine non trovato con ID: " + id);
    }

    @Override
    public void deleteOrder(Long id, String jwt) throws Exception {

        User u = userService.findUserByJwt(jwt);

        if (!u.getRole().equals(User.Role.ADMIN)){
            throw new Exception("ADMIN PRIVILEGES REQUIRED");
        }
        orderRepository.deleteById(id);
    }

    @Override
    public Ordine updateOrder(Ordine ordine, Long id) throws Exception {

        Ordine oldOrdine = findOrderById(id);

        if(ordine.getUser()!=null){
            oldOrdine.setUser(ordine.getUser());
        }
        if(ordine.getProduct()!=null){
            oldOrdine.setProduct(ordine.getProduct());
        }
        if(ordine.getQuantita()!=null){
            oldOrdine.setQuantita(ordine.getQuantita());
        }
        if(ordine.getPrezzo()!=null){
            oldOrdine.setPrezzo(ordine.getPrezzo());
        }
        if(ordine.getStato()!=null){
            oldOrdine.setStato(ordine.getStato());
        }
        return orderRepository.save(oldOrdine);
    }
}
