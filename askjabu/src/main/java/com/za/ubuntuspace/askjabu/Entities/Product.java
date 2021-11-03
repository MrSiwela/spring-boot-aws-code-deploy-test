package com.za.ubuntuspace.askjabu.Entities;

import javax.persistence.*;

@Entity
@Table( name = "product_tbl")
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    @Column( nullable = false, unique = true, length = 45)
    private String productName;
    @Column( nullable = false, length = 200)
    private String productDescription;

    public Product() {
    }

    public Product(int id, String productName, String productDescription) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
