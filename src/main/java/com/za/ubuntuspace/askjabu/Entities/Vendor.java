package com.za.ubuntuspace.askjabu.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 45,unique = true)
    private String vendorName;
    @Column(nullable = false)
    private String sector;
    @Column(nullable = false)
    private String operatingHours;
    @Column( nullable = true, length = 45)
    private String vendorImage;

    public Vendor() {
    }

    public Vendor(int id, String vendorName, String sector, String operatingHours, String vendorImage) {
        this.id = id;
        this.vendorName = vendorName;
        this.sector = sector;
        this.operatingHours = operatingHours;
        this.vendorImage = vendorImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getVendorImage() {
        return vendorImage;
    }

    public void setVendorImage(String vendorImage) {
        this.vendorImage = vendorImage;
    }

    @Transient
    public String getVendorImagePath(){
        if(vendorImage == null || ((Integer) id) == null){
            return null;
        }

        return "/uploads/vendors/"+id+"/"+vendorImage;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", vendorName='" + vendorName + '\'' +
                ", sector='" + sector + '\'' +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}
