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
    @Column( nullable = false)
    private float price;
    @Column( nullable = true, length = 45)
    private String firstImage;
    @Column( nullable = true, length = 45)
    private String secondImage;
    @Column( nullable = true, length = 45)
    private String thirdImage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    public Product() {
    }

    public Product(int id, String productName, String productDescription, float price, Category category, Vendor vendor) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.category = category;
        this.vendor = vendor;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getSecondImage() {
        return secondImage;
    }

    public void setSecondImage(String secondImage) {
        this.secondImage = secondImage;
    }

    public String getThirdImage() {
        return thirdImage;
    }

    public void setThirdImage(String thirdImage) {
        this.thirdImage = thirdImage;
    }

    @Transient
    public String getFirstImagePath(){
        if(firstImage == null || ((Integer) id) == null){
            return null;
        }

        return "/uploads/inventory/"+id+"/"+firstImage;
    }

    @Transient
    public String getSecondImagePath(){
        if(secondImage == null || ((Integer) id) == null){
            return null;
        }

        return "/uploads/inventory/"+id+"/"+secondImage;
    }

    @Transient
    public String getThirdImagePath(){
        if(thirdImage == null || ((Integer) id) == null){
            return null;
        }

        return "/uploads/inventory/"+id+"/"+thirdImage;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
