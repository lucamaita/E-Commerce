package com.SCAI.ecommerce.service;

import com.SCAI.ecommerce.model.Ordine;
import com.SCAI.ecommerce.model.Product;
import com.SCAI.ecommerce.model.User;

import java.util.List;
//Vedi commento in ProductService
public interface OrderService {

    public List<Ordine> findAll(String jwt) throws Exception;
    public Ordine insertOrder(Ordine ordine, User user, Product product);
    public Ordine findOrderById(Long id) throws Exception;
    public void deleteOrder(Long id, String jwt) throws Exception;
    public Ordine updateOrder(Ordine ordine, Long id) throws Exception;

}
