package com.SCAI.ecommerce.controller;

import com.SCAI.ecommerce.service.OrderService;
import com.SCAI.ecommerce.service.UserService;
import com.SCAI.ecommerce.model.Ordine;
import com.SCAI.ecommerce.model.Product;
import com.SCAI.ecommerce.model.User;
import com.SCAI.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// OrderController: un controller che gestisce le richieste CRUD (Create, Read, Update, Delete) per gli ordini.
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/findAll")
    public List<Ordine> findAll(@RequestHeader("Authorization") String jwt) throws Exception{
        List<Ordine> ordines = orderService.findAll(jwt);
        return ordines;
    }

    @PostMapping("/insert/{productId}")
    public Ordine insertOrder(@RequestBody Ordine ordine,
                              @PathVariable Long productId,
                              @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Product product = productService.findProductById(productId);
        Ordine insertedOrdine = orderService.insertOrder(ordine, user, product);
        return insertedOrdine;
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Long orderId,
                              @RequestHeader("Authorization") String jwt) throws Exception{
        orderService.deleteOrder(orderId, jwt);
        return "Order Deleted Successfully";
    }

    @PutMapping("/update/{id}")
    public Ordine updateOrder(@RequestBody Ordine ordine,
                              @PathVariable Long id) throws Exception{
        Ordine updatedOrdine = orderService.updateOrder(ordine, id);
        return updatedOrdine;
    }

}
