package com.maita.prova.service;

import com.maita.prova.model.Ordine;
import com.maita.prova.model.Product;
import com.maita.prova.model.User;

import java.util.List;

public interface OrderService {

    public List<Ordine> findAll();
    public Ordine insertOrder(Ordine ordine, User user, Product product);
    public Ordine findOrderById(Long id) throws Exception;
    public void deleteOrder(Long id) throws Exception;
    public Ordine updateOrder(Ordine ordine, Long id) throws Exception;

}
