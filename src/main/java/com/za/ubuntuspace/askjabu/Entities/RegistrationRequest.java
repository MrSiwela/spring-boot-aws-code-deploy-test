package com.za.ubuntuspace.askjabu.Entities;

public class RegistrationRequest {

    private String fullName;
    private String email;
    private String password;
    private String vendorId;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String fullName, String email, String password, String vendorId) {
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

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
