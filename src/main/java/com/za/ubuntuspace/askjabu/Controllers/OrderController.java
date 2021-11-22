package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Entities.Order;
import com.za.ubuntuspace.askjabu.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String ordersPage(Model model){
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders",orders);
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetailsPage(Model model, @PathVariable("id") int id){
        Order order = orderService.getOrderById(id).get();
        model.addAttribute("order",order);
        return "orderDetails";
    }

    @PostMapping("/orders/edit")
    public String editOrder(Order order){
        orderService.editOrder(order);
        return "redirect:/orders";
    }

}
