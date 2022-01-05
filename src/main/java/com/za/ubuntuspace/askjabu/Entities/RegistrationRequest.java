package com.za.ubuntuspace.askjabu.Entities;

public class RegistrationRequest {

    private String fullName;
    private String email;
    private String password;
    private int vendorId;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String fullName, String email, String password, int vendorId) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.vendorId = vendorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
