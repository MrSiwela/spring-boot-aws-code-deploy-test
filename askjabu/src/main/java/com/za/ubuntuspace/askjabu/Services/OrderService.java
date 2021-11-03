package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.Order;
import com.za.ubuntuspace.askjabu.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    public Optional<Order> getOrderById(int id){
        return ordersRepository.findById(id);
    }

    public List<Order> getAllOrders(){
        return ordersRepository.findAll();
    }

    public Order addNewOrder(Order order){
        return ordersRepository.save(order);
    }

    public Order editOrder(Order order){
        Order editOrder = getOrderById(order.getId()).get();
        editOrder.setDeliveryStatus(order.getDeliveryStatus());
        return ordersRepository.save(editOrder);
    }
}
