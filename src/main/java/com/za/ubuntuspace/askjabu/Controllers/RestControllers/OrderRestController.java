package com.za.ubuntuspace.askjabu.Controllers.RestControllers;

import com.za.ubuntuspace.askjabu.Entities.Order;
import com.za.ubuntuspace.askjabu.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/customer/{id}")
    @ResponseBody
    public List<Order> getCustomerOrders(@PathVariable("id") int customerId){
        return orderService.getOrdersByCustomerId(customerId);
    }

    @PostMapping("/orders/customer/new")
    @ResponseBody
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order){
        Order savedOrder = orderService.addNewOrder(order);
        try{
            return ResponseEntity.created(new URI("/api/v1/products/"+savedOrder.getId()))
                    .eTag(Integer.toString(savedOrder.getId()))
                    .body(savedOrder);
        }catch(URISyntaxException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
