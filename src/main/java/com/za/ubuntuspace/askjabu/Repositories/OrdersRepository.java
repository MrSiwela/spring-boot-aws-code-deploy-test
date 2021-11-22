package com.za.ubuntuspace.askjabu.Repositories;

import com.za.ubuntuspace.askjabu.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Order,Integer> {

    public List<Order> findAllByCustomerId(int customerId);

    public List<Order> findAllByDeliveryStatus(String deliveryStatus);
}
