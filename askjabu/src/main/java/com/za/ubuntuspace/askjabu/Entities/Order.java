package com.za.ubuntuspace.askjabu.Entities;

import javax.persistence.*;

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

    public Order() {
    }

    public Order(int id, String orderDate, String deliveryStatus) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryStatus = deliveryStatus;
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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
