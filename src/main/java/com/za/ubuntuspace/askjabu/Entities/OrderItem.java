package com.za.ubuntuspace.askjabu.Entities;

import javax.persistence.*;

@Entity
@Table( name = "order_items")
public class OrderItem {
    @Id
    @Column( name = "item_id")
    private int id;
    private String itemName;
}
