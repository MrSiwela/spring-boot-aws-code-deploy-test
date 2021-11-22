package com.za.ubuntuspace.askjabu.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String orderDate;
    @Column(nullable = false)
    private String deliveryStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany( targetEntity = OrderItem.class ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "order_id",referencedColumnName = "id")
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(int id, String orderDate, String deliveryStatus, Customer customer, List<OrderItem> orderItems) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryStatus = deliveryStatus;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                +
                '}';
    }
}
