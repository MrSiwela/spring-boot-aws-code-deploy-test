package com.za.ubuntuspace.askjabu.Entities;

import javax.persistence.*;

@Entity
@Table( name = "order_items")
public class OrderItem{
    @Id
    @Column( name = "item_id")
    private int id;
    private String itemName;
    private String itemDescription;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    public OrderItem() {
    }

    public OrderItem(int id, String itemName, String itemDescription, int quantity, Vendor vendor) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", quantity=" + quantity +
                ", vendor=" + vendor +
                '}';
    }
}
